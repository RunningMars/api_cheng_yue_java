package com.cheng.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value="member_image")
public class MemberImage extends Model<MemberImage> {

        @TableId(type= IdType.AUTO)
        private Integer id;

        private Integer memberId;

        private String url;

        private Integer isCover;

        private String uploadUrl;

        private Integer isAudit;

        @TableField(fill= FieldFill.INSERT)
        private Date createdAt;

        @TableField(fill=FieldFill.INSERT_UPDATE)
        private Date updatedAt;

        //@TableLogic //软删除注解
        private Date deletedAt;

}
