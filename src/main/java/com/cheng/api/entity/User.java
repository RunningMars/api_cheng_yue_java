package com.cheng.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cheng.api.dto.UserDto;
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
 * @since 2024-06-19
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("user")
@ApiModel(value = "User对象", description = "")
public class User extends BaseEntity {

    private static final Long serialVersionUID = 1L;

    public User(){}

    public User(UserDto userDto){
        this.mobile = userDto.getMobile();
        this.password = userDto.getPassword();
    }

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("手机号")
    @TableField("mobile")
    private String mobile;

    @TableField("email")
    private String email;

    @ApiModelProperty("姓名")
    @TableField("name")
    private String name;

    @TableField("email_verified_at")
    private Date emailVerifiedAt;

    @TableField("password")
    private String password;

    @TableField("remember_token")
    private String rememberToken;

    @TableField("created_at")
    private Date createdAt;

    @TableField("updated_at")
    private Date updatedAt;


}
