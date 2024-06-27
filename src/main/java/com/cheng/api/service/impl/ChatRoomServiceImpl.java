package com.cheng.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cheng.api.context.UserThreadLocal;
import com.cheng.api.dto.ChatRoomDto;
import com.cheng.api.dto.ChatRoomMemberDto;
import com.cheng.api.dto.ChatRoomMessageDto;
import com.cheng.api.dto.SysUserDto;
import com.cheng.api.dto.query.ChatRoomMessageQuery;
import com.cheng.api.entity.ChatRoom;
import com.cheng.api.entity.ChatRoomMember;
import com.cheng.api.entity.ChatRoomMessage;
import com.cheng.api.entity.Member;
import com.cheng.api.mapper.ChatRoomMapper;
import com.cheng.api.mapper.ChatRoomMemberMapper;
import com.cheng.api.mapper.ChatRoomMessageMapper;
import com.cheng.api.mapper.MemberMapper;
import com.cheng.api.service.ChatRoomService;
import com.cheng.api.vo.MemberVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author rdg
 * @since 2024-06-21
 */
@Service
public class ChatRoomServiceImpl extends ServiceImpl<ChatRoomMapper, ChatRoom> implements ChatRoomService {

    @Autowired
    ChatRoomMapper chatRoomMapper;

    @Autowired
    ChatRoomMemberMapper chatRoomMemberMapper;

    @Autowired
    ChatRoomMessageMapper chatRoomMessageMapper;

    @Autowired
    MemberMapper memberMapper;

    @Override
    public Page<ChatRoomDto> chatRoomList(HashMap<String,Object> params) {
        SysUserDto currentUser = UserThreadLocal.getCurrentUser();
        System.out.println("currentUser");
        System.out.println(currentUser);
        // 使用 Optional 来避免深层次的 null 检查
        Optional<Integer> memberId = Optional.ofNullable(currentUser)
                .map(SysUserDto::getMember)
                .map(Member::getId);//.orElse(20003L); 如果任何一个值为 null，使用默认值 "20003L"
        if (memberId.isEmpty()){
            System.out.println("memberId.isEmpty()");
            return null;
        }
        long pageNum = Long.parseLong((String) params.getOrDefault(("pageNum"),"1"));
        long pageSize = Long.parseLong((String)params.getOrDefault("pageSize","10"));
        Page<ChatRoomDto> tPage = new Page<>(pageNum, pageSize);

        Object keyWord = params.get("keyWord");
        String keyWordParam = Objects.isNull(keyWord) ? null : (String)keyWord;

        Page<ChatRoomDto> chatRoomPage = chatRoomMapper.chatRoomList(tPage, memberId.get(), keyWordParam);

        if (chatRoomPage.getRecords().isEmpty()){
            return null;
        }
        // 根据 roomIds
        Set<Integer> roomIds = chatRoomPage.getRecords().stream().map(ChatRoom::getId).collect(Collectors.toSet());

        // 查找关联 chat_room_member_opposite
        List<ChatRoomMemberDto> chatRoomOppositeMembers = chatRoomMemberMapper.selectChatRoomMember(memberId.get(), roomIds);
        Map<Integer, List<ChatRoomMemberDto>> listOppositeMap = chatRoomOppositeMembers.stream().collect(Collectors.groupingBy(ChatRoomMember::getChatRoomId));

        // 查找关联 chat_room_member_me
        List<ChatRoomMember> chatRoomMeMembers = chatRoomMemberMapper.selectChatRoomMeMember(memberId.get(), roomIds);
        Map<Integer, List<ChatRoomMember>> listMeMap = chatRoomMeMembers.stream().collect(Collectors.groupingBy(ChatRoomMember::getChatRoomId));

        chatRoomPage.getRecords().forEach(i -> {
            i.setChatRoomOppositeMember(listOppositeMap.get(i.getId()));
            i.setChatRoomMeMember(listMeMap.get(i.getId()));
            // 查找关联 chat_room_last_message
            ChatRoomMessageDto chatRoomLastMessage = chatRoomMessageMapper.getChatRoomLastMessage(i.getId());
            i.setChatRoomLastMessage(chatRoomLastMessage);
        });
        return chatRoomPage;
    }


    public HashMap<String, Object> getChatRoomMessage(ChatRoomMessageQuery chatRoomMessageQuery) {

        SysUserDto currentUser = UserThreadLocal.getCurrentUser();
        System.out.println("currentUser:" + currentUser);
        // 使用 Optional 来避免深层次的 null 检查
        Optional<Integer> memberId = Optional.ofNullable(currentUser)
                .map(SysUserDto::getMember)
                .map(Member::getId);//.orElse(20003L); 如果任何一个值为 null，使用默认值 "20003L"
        if (memberId.isEmpty()){
            System.out.println("memberId.isEmpty()");
            return null;
        }

        HashMap<String, Object> returnMap = new HashMap<>();

        Integer chatRoomId;

        if (chatRoomMessageQuery.getChatRoomId() == null){
            LambdaQueryWrapper<ChatRoom> chatRoomLambdaQueryWrapper = Wrappers.lambdaQuery(ChatRoom.class);
            chatRoomLambdaQueryWrapper.exists("SELECT chat_room_member.id FROM chat_room_member WHERE chat_room_member.chat_room_id = chat_room.id and chat_room_member.member_id = {0} ",memberId.get());
            chatRoomLambdaQueryWrapper.exists("SELECT chat_room_member.id FROM chat_room_member WHERE chat_room_member.chat_room_id = chat_room.id and chat_room_member.member_id = {0} ",chatRoomMessageQuery.getToMemberId());
            chatRoomLambdaQueryWrapper.last("limit 1");
            ChatRoom existChatRoom = chatRoomMapper.selectOne(chatRoomLambdaQueryWrapper);
            System.out.println(existChatRoom);
            Member member = memberMapper.selectById(chatRoomMessageQuery.getToMemberId());
            if (member != null){
                MemberVo memberVo = new MemberVo(member);
                returnMap.put("toMember",memberVo);
            }

            if (existChatRoom == null){
                returnMap.put("data",new ArrayList());
                return returnMap;
            }
            chatRoomId = existChatRoom.getId();
        }else{
            chatRoomId = chatRoomMessageQuery.getChatRoomId();
        }
        ChatRoomMember chatRoomMember = chatRoomMemberMapper.selectOne(Wrappers.lambdaQuery(ChatRoomMember.class)
                .eq(ChatRoomMember::getChatRoomId, chatRoomMessageQuery.getChatRoomId())
                .ne(ChatRoomMember::getMemberId, memberId.get())
                .last("limit 1"));
        if (chatRoomMember != null){
            Member member = memberMapper.selectById(chatRoomMember.getMemberId());
            MemberVo memberVo = new MemberVo(member);
            returnMap.put("toMember",memberVo);
        }
        Page<ChatRoomMessage> pageInfo = new Page<>(chatRoomMessageQuery.getPageNum(),chatRoomMessageQuery.getPageSize());
        Page<ChatRoomMessageDto> chatRoomMessageList = chatRoomMessageMapper.getMessageByChatRoomId(pageInfo, chatRoomMessageQuery.getChatRoomId());
        Collections.reverse(chatRoomMessageList.getRecords());

        returnMap.put("data",chatRoomMessageList);
        return returnMap;

    }

    public HashMap<String, Object> unreadChatCount(Integer id) {

        Long unreadCount = chatRoomMemberMapper.selectCount(Wrappers.lambdaQuery(ChatRoomMember.class).eq(ChatRoomMember::getMemberId, id).eq(ChatRoomMember::getIsNewToRead, true));

        HashMap<String, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("unreadChatCount",unreadCount);

        return objectObjectHashMap;
    }
}
