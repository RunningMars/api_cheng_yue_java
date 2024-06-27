package com.cheng.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cheng.api.context.UserThreadLocal;
import com.cheng.api.dto.SysUserDto;
import com.cheng.api.dto.query.SendMessageQuery;
import com.cheng.api.entity.ChatRoom;
import com.cheng.api.entity.ChatRoomMember;
import com.cheng.api.entity.ChatRoomMessage;
import com.cheng.api.entity.Member;
import com.cheng.api.exception.CustomException;
import com.cheng.api.mapper.ChatRoomMapper;
import com.cheng.api.mapper.ChatRoomMemberMapper;
import com.cheng.api.mapper.ChatRoomMessageMapper;
import com.cheng.api.service.ChatRoomMessageService;
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
public class ChatRoomMessageServiceImpl extends ServiceImpl<ChatRoomMessageMapper, ChatRoomMessage> implements ChatRoomMessageService {

    @Autowired
    ChatRoomMapper chatRoomMapper;

    @Autowired
    ChatRoomMemberMapper chatRoomMemberMapper;

    @Autowired
    ChatRoomMessageMapper chatRoomMessageMapper;

    public Boolean sendMessage(SendMessageQuery sendMessageQuery){
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
        if (currentMemberId.get().equals(sendMessageQuery.getToMemberId())){
            throw new CustomException("无法给自己发送消息");
        }

        //找到 chatRoom
        LambdaQueryWrapper<ChatRoom> chatRoomLambdaQueryWrapper = Wrappers.lambdaQuery(ChatRoom.class);
        chatRoomLambdaQueryWrapper.exists("SELECT chat_room_member.id FROM chat_room_member WHERE chat_room_member.chat_room_id = chat_room.id and chat_room_member.member_id = {0} ",currentMemberId.get());
        chatRoomLambdaQueryWrapper.exists("SELECT chat_room_member.id FROM chat_room_member WHERE chat_room_member.chat_room_id = chat_room.id and chat_room_member.member_id = {0} ",sendMessageQuery.getToMemberId());
        chatRoomLambdaQueryWrapper.last("limit 1");
        ChatRoom existChatRoom = chatRoomMapper.selectOne(chatRoomLambdaQueryWrapper);
        System.out.println(existChatRoom);
        Integer existChatRoomId;
        //没有则创建 chatRoom
        if (existChatRoom == null){
            ChatRoom chatRoom = new ChatRoom();
            chatRoom.setChatRoomName(currentMemberId + "_" + sendMessageQuery.getToMemberId());
            chatRoomMapper.insert(chatRoom);
            // 插入后，entity的id字段将被自动回填
            System.out.println("插入的ID为：" + chatRoom.getId());

            ChatRoomMember chatRoomMeMember = new ChatRoomMember();
            chatRoomMeMember.setChatRoomId(chatRoom.getId());
            chatRoomMeMember.setMemberId(currentMemberId.get());
            chatRoomMemberMapper.insert(chatRoomMeMember);

            ChatRoomMember chatRoomToMember = new ChatRoomMember();
            chatRoomToMember.setChatRoomId(chatRoom.getId());
            chatRoomToMember.setMemberId(sendMessageQuery.getToMemberId());
            chatRoomToMember.setIsNewToRead(true);
            chatRoomMemberMapper.insert(chatRoomToMember);

            existChatRoomId = chatRoom.getId();

        }else{

            existChatRoomId = existChatRoom.getId();

            ChatRoomMember chatRoomToMember = chatRoomMemberMapper.selectOne(Wrappers.lambdaQuery(ChatRoomMember.class).eq(ChatRoomMember::getMemberId, sendMessageQuery.getToMemberId()).last("limit 1"));

            if (chatRoomToMember == null){
                throw new CustomException("消息发送对象不存在");
            }
            chatRoomToMember.setIsNewToRead(true);
            chatRoomMemberMapper.updateById(chatRoomToMember);
        }

        ChatRoomMessage chatRoomMessage = new ChatRoomMessage();

        chatRoomMessage.setChatRoomId(existChatRoomId);
        chatRoomMessage.setFromMemberId(currentMemberId.get());
        chatRoomMessage.setToMemberId(sendMessageQuery.getToMemberId());
        chatRoomMessage.setMessage(sendMessageQuery.getMessage());
        chatRoomMessageMapper.insert(chatRoomMessage);
        return true;
    }

}
