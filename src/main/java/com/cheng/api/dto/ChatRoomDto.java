package com.cheng.api.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.cheng.api.entity.ChatRoom;
import com.cheng.api.entity.ChatRoomMember;
import lombok.Data;

import java.util.List;

@Data
public class ChatRoomDto extends ChatRoom {

    // 非数据库字段，用于存储关联查询结果
    @TableField(exist = false)
    private List<ChatRoomMemberDto> chatRoomOppositeMember;


    // 非数据库字段，用于存储关联查询结果
    @TableField(exist = false)
    private List<ChatRoomMember> chatRoomMeMember;

    // 非数据库字段，用于存储关联查询结果
    @TableField(exist = false)
    private ChatRoomMessageDto chatRoomLastMessage;


}
