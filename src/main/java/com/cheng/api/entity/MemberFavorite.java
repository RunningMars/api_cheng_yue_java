package com.cheng.api.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cheng.api.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

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
@TableName("member_favorite")
@ApiModel(value = "MemberFavorite对象", description = "")
public class MemberFavorite extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("member_id")
    private Integer memberId;

    @TableField("to_member_id")
    private Integer toMemberId;

    @ApiModelProperty("0:未收藏,1:已收藏")
    @TableField("is_favorite")
    private Boolean isFavorite;

    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private Date createdAt;

    @TableField(value = "updated_at", fill = FieldFill.UPDATE)
    private Date updatedAt;


}
