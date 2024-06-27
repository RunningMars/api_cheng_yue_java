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
 * @since 2024-06-26
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("mobile_validate_code")
@ApiModel(value = "MobileValidateCode对象", description = "")
public class MobileValidateCode extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("mobile")
    private String mobile;

    @TableField("code")
    private String code;

    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private Date createdAt;

    @TableField("created_by")
    private Integer createdBy;


}
