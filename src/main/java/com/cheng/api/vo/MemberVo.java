package com.cheng.api.vo;

import com.cheng.api.entity.Member;
import lombok.Data;

@Data
public class MemberVo {

    private Integer id;

    private String nickName;

    private String profilePhoto;

    public MemberVo() {

    }

    public MemberVo(Member member) {
        this.id = member.getId();
        this.nickName = member.getNickName();
        this.profilePhoto = member.getProfilePhoto();
    }
}
