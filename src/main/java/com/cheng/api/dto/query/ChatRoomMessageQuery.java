package com.cheng.api.dto.query;

import com.cheng.api.dto.PageInfo;
import lombok.Data;

@Data
public class ChatRoomMessageQuery extends PageInfo {

    private Integer chatRoomId;

    private Integer toMemberId;
}
