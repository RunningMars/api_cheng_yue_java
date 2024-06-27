package com.cheng.api.dto;

import com.cheng.api.entity.Member;
import com.cheng.api.entity.User;
import lombok.Data;

import java.util.Date;

@Data
public class SysUserDto {

        private Integer id;

        private String name;

        private String email;

        private Date emailVerifiedAt;

        private Date createdAt;

        private Date updatedAt;

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    private Member member;

        public SysUserDto(){
        }

        public SysUserDto(User user){
            this.id = user.getId();
            this.name = user.getName();
            this.email = user.getEmail();
            this.emailVerifiedAt = user.getEmailVerifiedAt();
        }
}
