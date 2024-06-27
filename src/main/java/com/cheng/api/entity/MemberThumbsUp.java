package com.cheng.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author rdg
 * @since 2024-06-25
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("member_thumbs_up")
@ApiModel(value = "MemberThumbsUp对象", description = "")
public class MemberThumbsUp extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("member_id")
    private Integer memberId;

    @TableField("to_member_id")
    private Integer toMemberId;

    @ApiModelProperty("0:未点赞,1:已点赞")
    @TableField("is_thumbs_up")
    private Boolean isThumbsUp;

    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private Date createdAt;

    @TableField(value = "updated_at", fill = FieldFill.UPDATE)
    private Date updatedAt;


}
