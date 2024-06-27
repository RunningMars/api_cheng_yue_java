package com.cheng.api.dto.query;

import lombok.Data;

@Data
public class SendMessageQuery {

    private Integer toMemberId;

    private String message;
}
