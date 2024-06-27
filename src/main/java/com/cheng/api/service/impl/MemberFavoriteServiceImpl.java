package com.cheng.api.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cheng.api.context.UserThreadLocal;
import com.cheng.api.dto.SysUserDto;
import com.cheng.api.entity.Member;
import com.cheng.api.entity.MemberFavorite;
import com.cheng.api.exception.CustomException;
import com.cheng.api.mapper.MemberFavoriteMapper;
import com.cheng.api.service.MemberFavoriteService;
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
public class MemberFavoriteServiceImpl extends ServiceImpl<MemberFavoriteMapper, MemberFavorite> implements MemberFavoriteService {

    @Autowired
    MemberFavoriteMapper memberFavoriteMapper;

    public Boolean updateMyFavorite(Integer toMemberId, Integer isFavorite){
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
            throw new CustomException("无法将自己加入收藏夹");
        }

        if (isFavorite > 0){
            MemberFavorite memberFavorite = memberFavoriteMapper.selectOne(Wrappers.lambdaQuery(MemberFavorite.class)
                    .eq(MemberFavorite::getMemberId, currentMemberId.get())
                    .eq(MemberFavorite::getToMemberId, toMemberId)
                    .last("limit 1"));
            if (memberFavorite == null){
                MemberFavorite memberFavoriteObj = new MemberFavorite();
                memberFavoriteObj.setMemberId(currentMemberId.get());
                memberFavoriteObj.setToMemberId(toMemberId);
                memberFavoriteMapper.insert(memberFavoriteObj);
            }
        }else{
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("member_id",currentMemberId.get());
            hashMap.put("to_member_id",toMemberId);
            memberFavoriteMapper.deleteByMap(hashMap);
        }

        return true;
    }
}
