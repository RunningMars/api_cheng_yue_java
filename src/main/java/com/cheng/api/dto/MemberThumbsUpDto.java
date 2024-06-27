package com.cheng.api.dto;

import com.cheng.api.vo.MemberVo;
import lombok.Data;

import java.util.Date;

@Data
public class MemberThumbsUpDto {

    private Integer id;

    private Integer memberId;

    private Integer toMemberId;

    private Boolean isThumbsUp;

    private Date createdAt;

    private Date updatedAt;

    private MemberVo member;
}
