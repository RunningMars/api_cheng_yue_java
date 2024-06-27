package com.cheng.api.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cheng.api.context.UserThreadLocal;
import com.cheng.api.dto.SysUserDto;
import com.cheng.api.entity.ChatRoomMember;
import com.cheng.api.entity.Member;
import com.cheng.api.mapper.ChatRoomMemberMapper;
import com.cheng.api.service.ChatRoomMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author rdg
 * @since 2024-06-21
 */
@Service
public class ChatRoomMemberServiceImpl extends ServiceImpl<ChatRoomMemberMapper, ChatRoomMember> implements ChatRoomMemberService {

    @Autowired
    ChatRoomMemberMapper chatRoomMemberMapper;

    public Boolean readAll() {
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
        ChatRoomMember chatRoomMember = new ChatRoomMember();
        chatRoomMember.setIsNewToRead(false);
        chatRoomMemberMapper.update(chatRoomMember, Wrappers.lambdaQuery(ChatRoomMember.class)
                .eq(ChatRoomMember::getMemberId,currentMemberId.get()));
        return true;
    }
}
