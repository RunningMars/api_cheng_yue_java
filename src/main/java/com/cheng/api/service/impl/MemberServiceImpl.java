package com.cheng.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cheng.api.context.UserThreadLocal;
import com.cheng.api.dto.MemberDto;
import com.cheng.api.dto.MemberQuery;
import com.cheng.api.dto.SysUserDto;
import com.cheng.api.dto.query.EditMemberQuery;
import com.cheng.api.entity.*;
import com.cheng.api.exception.CustomException;
import com.cheng.api.mapper.*;
import com.cheng.api.service.MemberServiceI;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberServiceI {

    @Autowired
    MemberMapper memberMapper;

    @Autowired
    MemberImageMapper memberImageMapper;

    @Autowired
    MemberRequestMapper memberRequestMapper;

    @Autowired
    MemberThumbsUpMapper memberThumbsUpMapper;

    @Autowired
    MemberFavoriteMapper memberFavoriteMapper;


    @Autowired
    MemberImageServiceImpl memberImageService;


    @Override
    public Page<MemberDto> memberList(MemberQuery memberQuery) {
        //System.out.println(memberQuery);
        if (Objects.isNull(memberQuery)){
            memberQuery = new MemberQuery();
        }
        //System.out.println(memberQuery);
        Page<Member> page = new Page<>(memberQuery.getPageNum(),memberQuery.getPageSize());

        SysUserDto currentUser = UserThreadLocal.getCurrentUser();
        // 使用 Optional 来避免深层次的 null 检查
        Optional<Integer> memberId = Optional.ofNullable(currentUser)
                .map(SysUserDto::getMember)
                .map(Member::getId);//.orElse(20003L); 如果任何一个值为 null，使用默认值 "20003L"
        // 使用 Optional 来避免深层次的 null 检查
        Optional<Integer> sex = Optional.ofNullable(currentUser)
                .map(SysUserDto::getMember)
                .map(Member::getSex);//.orElse(20003L); 如果任何一个值为 null，使用默认值 "20003L"
        if (memberId.isEmpty()){
            System.out.println("memberId.isEmpty()");
            return null;
        }
        if (sex.isEmpty()){
            System.out.println("sex.isEmpty()");
            throw new CustomException("请先编辑性别");
        }
        Integer currentMemberSex = sex.get();
        Integer needMemberSex = currentMemberSex == 2 ? 1 : 2;
        memberQuery.setSex(needMemberSex);

        LambdaQueryWrapper<Member> memberLambdaQueryWrapper = memberQeuryBuilder(memberQuery,memberId.get());

        Page<Member> pageResult = page(page, memberLambdaQueryWrapper);

        Page<MemberDto> returnPageResult = new Page<MemberDto>();

        if (pageResult.getRecords().isEmpty()){
            return returnPageResult;
        }
        Set<Integer> memberIds = pageResult.getRecords().stream().map(Member::getId).collect(Collectors.toSet());

        List<MemberImage> memberImages = memberImageMapper.selectList(Wrappers.lambdaQuery(MemberImage.class).in(!memberIds.isEmpty(),MemberImage::getMemberId,memberIds));
        //Map<Integer, MemberImage> imageMap = memberImages.stream().collect(Collectors.toMap(MemberImage::getMemberId, memberImage -> memberImage));

        Map<Integer, List<MemberImage>> imageMap = memberImages.stream().collect(Collectors.groupingBy(MemberImage::getMemberId));

        List<MemberDto> memberDtoList = new ArrayList<>();

        pageResult.getRecords().forEach( item -> {
            List<MemberImage> memberImage = imageMap.get(item.getId());
            MemberDto memberDto= new MemberDto(item);
            memberDto.setMemberImages(memberImage);
            memberDtoList.add(memberDto);
        });

        BeanUtils.copyProperties(pageResult,returnPageResult);
        returnPageResult.setRecords(memberDtoList);

        return returnPageResult;
    }

    private LambdaQueryWrapper<Member> memberQeuryBuilder(MemberQuery memberQuery,Integer currentMemberId){

        System.out.println("currentMemberId:"+currentMemberId);

        LambdaQueryWrapper<Member> memberQueryWrapper = new LambdaQueryWrapper<>();

        memberQueryWrapper.eq(Member::getSex,memberQuery.getSex());

        memberQueryWrapper.eq(StringUtils.isNotBlank(memberQuery.getKeyWord()),Member::getNickName,memberQuery.getKeyWord());

        memberQueryWrapper.eq(StringUtils.isNotBlank(memberQuery.getRealName()),Member::getRealName,memberQuery.getRealName());

        memberQueryWrapper.ge(!Objects.isNull(memberQuery.getAgeMinRequest()) && memberQuery.getAgeMinRequest() > 0,Member::getAge,memberQuery.getAgeMinRequest());

        memberQueryWrapper.le(!Objects.isNull(memberQuery.getAgeMaxRequest()) && memberQuery.getAgeMaxRequest() > 0,Member::getAge,memberQuery.getAgeMaxRequest());

        memberQueryWrapper.ge(!Objects.isNull(memberQuery.getHeightMinRequest()) && memberQuery.getHeightMinRequest() > 0,Member::getHeight,memberQuery.getHeightMinRequest());

        memberQueryWrapper.le(!Objects.isNull(memberQuery.getHeightMaxRequest()) && memberQuery.getHeightMaxRequest() > 0,Member::getHeight,memberQuery.getHeightMaxRequest());

        memberQueryWrapper.ge(!Objects.isNull(memberQuery.getEducationBackgroundCodeRequest()) && memberQuery.getEducationBackgroundCodeRequest() > 0,Member::getEducationBackgroundCode,memberQuery.getEducationBackgroundCodeRequest());

        memberQueryWrapper.eq(StringUtils.isNotBlank(memberQuery.getAnnualIncomeRequest()),Member::getAnnualIncome,memberQuery.getAnnualIncomeRequest());

        memberQueryWrapper.ge(!Objects.isNull(memberQuery.getAnnualIncomeMinRequest()) && memberQuery.getAnnualIncomeMinRequest() > 0,Member::getAnnualIncomeMin,memberQuery.getAnnualIncomeMinRequest());

        memberQueryWrapper.eq(StringUtils.isNotBlank(memberQuery.getAssetCarRequest()),Member::getAssetCar,memberQuery.getAssetCarRequest());

        memberQueryWrapper.eq(StringUtils.isNotBlank(memberQuery.getWantChildRequest()),Member::getWantChild,memberQuery.getWantChildRequest());

        if (memberQuery.getAssetHouseRequest() instanceof String) {
            // 处理字符串
            memberQueryWrapper.eq(StringUtils.isNotBlank((String)memberQuery.getAssetHouseRequest()),Member::getAssetHouse,memberQuery.getAssetHouseRequest());
        } else if (memberQuery.getAssetHouseRequest() instanceof List) {
            // 处理字符串数组
            List<String> list = (List<String>) memberQuery.getAssetHouseRequest();
            memberQueryWrapper.in(!list.isEmpty(),Member::getAssetHouse,list);
        }

        if (memberQuery.getMaritalStatusRequest() instanceof String) {
            // 处理字符串
            memberQueryWrapper.eq(StringUtils.isNotBlank((String)memberQuery.getMaritalStatusRequest()),Member::getMaritalStatus,memberQuery.getMaritalStatusRequest());
        } else if (memberQuery.getMaritalStatusRequest() instanceof List) {
            // 处理字符串数组
            List<String> list = (List<String>) memberQuery.getMaritalStatusRequest();
            memberQueryWrapper.in(!list.isEmpty(),Member::getMaritalStatus,list);
        }

        // 搜索当前用户已收藏的
        if(memberQuery.getIsFavorite() != null && memberQuery.getIsFavorite() != ""  && !"0".equals(memberQuery.getIsFavorite())){
            memberQueryWrapper.exists("SELECT member_favorite.id FROM member_favorite WHERE member_favorite.member_id = {0} AND member_favorite.to_member_id = member.id",currentMemberId);
        }
        // 搜索当前用户已点赞的
        if(memberQuery.getIsThumbsUp() != null && memberQuery.getIsThumbsUp() != "" && !"0".equals(memberQuery.getIsThumbsUp())){
            memberQueryWrapper.exists("SELECT member_thumbs_up.id FROM member_thumbs_up WHERE member_thumbs_up.member_id = {0} AND member_thumbs_up.to_member_id = member.id",currentMemberId);
        }
        return memberQueryWrapper;
    }

    public MemberDto memberDetail(Integer id) {

        Member member = getById(id);

        if (member == null){ return null; }

        List<MemberImage> memberImages = memberImageMapper.selectList(Wrappers.lambdaQuery(MemberImage.class).eq(MemberImage::getMemberId,member.getId()));

        MemberRequest memberRequest = memberRequestMapper.selectOne(Wrappers.lambdaQuery(MemberRequest.class).eq(MemberRequest::getMemberId,member.getId()).last("limit 1"));

        List<MemberDto> memberDtoList = new ArrayList<>();

        MemberDto memberDto= new MemberDto(member);

        memberDto.setMemberImages(memberImages);

        memberDto.setMemberRequest(memberRequest);

        SysUserDto currentUser = UserThreadLocal.getCurrentUser();
        // 使用 Optional 来避免深层次的 null 检查
        Optional<Integer> currentMemberId = Optional.ofNullable(currentUser)
                .map(SysUserDto::getMember)
                .map(Member::getId);//.orElse(20003L); 如果任何一个值为 null，使用默认值 "20003L"
        if (!currentMemberId.isEmpty()){
            List<MemberThumbsUp> memberThumbsUpsList = memberThumbsUpMapper.selectList(Wrappers.lambdaQuery(MemberThumbsUp.class)
                    .eq(MemberThumbsUp::getMemberId, currentMemberId.get())
                    .eq(MemberThumbsUp::getToMemberId, id));
            memberDto.setMemberThumbsUpToMember(memberThumbsUpsList);

            List<MemberFavorite> memberFavoriteList = memberFavoriteMapper.selectList(Wrappers.lambdaQuery(MemberFavorite.class)
                    .eq(MemberFavorite::getMemberId, currentMemberId.get())
                    .eq(MemberFavorite::getToMemberId, id));
            memberDto.setMemberFavoriteToMember(memberFavoriteList);
        }

        return memberDto;
    }

    public Boolean saveMember(EditMemberQuery editMemberQuery) {

        Member member = new Member();
        BeanUtils.copyProperties(editMemberQuery,member);

        boolean b = Member.educationBackgroundMap.containsKey(editMemberQuery.getEducationBackground());

        if (Member.educationBackgroundMap.containsKey(editMemberQuery.getEducationBackground())){
            member.setEducationBackgroundCode(Member.educationBackgroundMap.get(editMemberQuery.getEducationBackground()));
        }

        if (Member.annualIncomeMap.containsKey(editMemberQuery.getAnnualIncome())){
            List list = Member.annualIncomeMap.get(editMemberQuery.getAnnualIncome());
            member.setAnnualIncomeMin( (Integer) Collections.min(list));
            member.setAnnualIncomeMax( (Integer) Collections.max(list));
        }

        if (editMemberQuery.getBirthDay() != null && !"".equals(editMemberQuery.getBirthDay()) ){
            LocalDate birthDate = LocalDate.parse(editMemberQuery.getBirthDay());
            int age = this.calculateAge(birthDate);
            member.setAge(age);
        }
        HashMap<String, Object> deleteMap = new HashMap<>();

        int i = memberMapper.updateById(member);
        if  (i <= 0 ){
            return false;
        }

        deleteMap.put("member_id",editMemberQuery.getId());
        memberImageMapper.deleteByMap(deleteMap);
        List<MemberImage> memberImages = editMemberQuery.getMemberImages();
        memberImages.forEach(m -> m.setMemberId(editMemberQuery.getId()));
        if (!memberImages.isEmpty()){
            memberImageService.saveBatch(memberImages);
        }

        memberRequestMapper.updateById(editMemberQuery.getMemberRequest());

        return true;
    }

    public static int calculateAge(LocalDate birthDate) {
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(birthDate, currentDate);
        return period.getYears();
    }
}
