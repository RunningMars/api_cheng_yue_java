package com.cheng.api.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class UserDto{

    private String mobile;

    private String password;

    private String code;

    @TableField(exist = false)
    private String passwordConfirmation;
}
