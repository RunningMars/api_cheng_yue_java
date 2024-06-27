package com.cheng.api.dto;

import com.cheng.api.entity.ChatRoomMessage;
import com.cheng.api.vo.MemberVo;
import lombok.Data;

@Data
public class ChatRoomMessageDto extends ChatRoomMessage {

    private MemberVo fromMember;

    private MemberVo toMember;
}
