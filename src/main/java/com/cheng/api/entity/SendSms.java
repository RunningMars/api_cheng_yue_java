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
@TableName("send_sms")
@ApiModel(value = "SendSms对象", description = "")
public class SendSms extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("mobile")
    private String mobile;

    @TableField("template_code")
    private String templateCode;

    @TableField("sign_name")
    private String signName;

    @TableField("template_param")
    private String templateParam;

    @TableField("message")
    private String message;

    @TableField("response")
    private String response;

    @TableField("code")
    private String code;

    @TableField("biz_id")
    private String bizId;

    @TableField("request_id")
    private String requestId;

    @TableField("status")
    private String status;

    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private Date createdAt;

    @TableField("created_by")
    private Integer createdBy;


}
