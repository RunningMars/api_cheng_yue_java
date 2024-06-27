package com.cheng.api.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.cheng.api.entity.*;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.List;


@Data
public class MemberDto extends Member {

        //一对多
        @TableField(exist = false)
        private List<MemberImage> memberImages;

        public MemberDto(Member member) {
            BeanUtils.copyProperties(member,this);
        }

        public List<MemberImage> getMemberImages() {
                return memberImages;
        }

        public void setMemberImages(List<MemberImage> memberImages) {
                this.memberImages = memberImages;
        }

        private MemberRequest memberRequest;

        private List<MemberThumbsUp> memberThumbsUpToMember;

        private List<MemberFavorite> memberFavoriteToMember;
}
