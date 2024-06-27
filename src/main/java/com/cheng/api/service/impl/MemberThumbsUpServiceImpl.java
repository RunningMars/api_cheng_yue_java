package com.cheng.api.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cheng.api.context.UserThreadLocal;
import com.cheng.api.dto.MemberThumbsUpDto;
import com.cheng.api.dto.SysUserDto;
import com.cheng.api.entity.Member;
import com.cheng.api.entity.MemberThumbsUp;
import com.cheng.api.exception.CustomException;
import com.cheng.api.mapper.MemberThumbsUpMapper;
import com.cheng.api.service.MemberThumbsUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author rdg
 * @since 2024-06-25
 */
@Service
public class MemberThumbsUpServiceImpl extends ServiceImpl<MemberThumbsUpMapper, MemberThumbsUp> implements MemberThumbsUpService {


    @Autowired
    MemberThumbsUpMapper memberThumbsUpMapper;

    public Boolean updateMyThumbsUp(Integer toMemberId, Integer isThumbsUp) {

        SysUserDto currentUser = UserThreadLocal.getCurrentUser();
        System.out.println("currentUser:"+currentUser);
        // 使用 Optional 来避免深层次的 null 检查
        Optional<Integer> currentMemberId = Optional.ofNullable(currentUser)
                .map(SysUserDto::getMember)
                .map(Member::getId);
        if (currentMemberId.isEmpty()){
            System.out.println("memberId.isEmpty()");
            return false;
        }
        if (currentMemberId.get().equals(toMemberId) ){
            throw new CustomException("无法给自己点赞");
        }

        if (isThumbsUp > 0){
            MemberThumbsUp memberThumbsUp = memberThumbsUpMapper.selectOne(Wrappers.lambdaQuery(MemberThumbsUp.class)
                    .eq(MemberThumbsUp::getMemberId, currentMemberId.get())
                    .eq(MemberThumbsUp::getToMemberId, toMemberId)
                    .last("limit 1"));
            if (memberThumbsUp == null){
                MemberThumbsUp memberThumbsUpObj = new MemberThumbsUp();
                memberThumbsUpObj.setMemberId(currentMemberId.get());
                memberThumbsUpObj.setToMemberId(toMemberId);
                memberThumbsUpMapper.insert(memberThumbsUpObj);
            }
        }else{
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("member_id",currentMemberId.get());
            hashMap.put("to_member_id",toMemberId);
            memberThumbsUpMapper.deleteByMap(hashMap);
        }

        return true;
    }

    public HashMap<String, Object> receiveMyThumbsUpList(String keyWord, Integer pageNum, Integer pageSize) {

        HashMap<String, Object> hashMap = new HashMap<>();

        SysUserDto currentUser = UserThreadLocal.getCurrentUser();
        System.out.println("currentUser:"+currentUser);
        // 使用 Optional 来避免深层次的 null 检查
        Optional<Integer> currentMemberId = Optional.ofNullable(currentUser)
                .map(SysUserDto::getMember)
                .map(Member::getId);
        if (currentMemberId.isEmpty()){
            System.out.println("memberId.isEmpty()");
            return hashMap;
        }
        Long totalThumbsUpCount = memberThumbsUpMapper.selectCount(Wrappers.lambdaQuery(MemberThumbsUp.class)
                .eq(MemberThumbsUp::getToMemberId, currentMemberId.get())
        );
        if (pageNum == null){
            pageNum = 1;
        }
        if (pageSize == null){
            pageSize = 10;
        }

        Page<MemberThumbsUp> objectPage = new Page<>(pageNum, pageSize);

        Page<MemberThumbsUpDto> memberThumbsUpDtoPage = memberThumbsUpMapper.searchThumbsUpList(objectPage, currentMemberId.get(), keyWord);

        hashMap.put("thumbsUpListPage",memberThumbsUpDtoPage);
        hashMap.put("allThumbsUpCount",totalThumbsUpCount);

        return hashMap;
    }
}
