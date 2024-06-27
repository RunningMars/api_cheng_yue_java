package com.cheng.api.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.cheng.api.entity.ChatRoomMember;
import com.cheng.api.vo.MemberVo;
import lombok.Data;

@Data
public class ChatRoomMemberDto extends ChatRoomMember {

    @TableField(exist = false)
    private MemberVo member;

}
