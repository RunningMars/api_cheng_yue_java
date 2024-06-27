package com.cheng.api.controller;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.cheng.api.common.R;
import com.cheng.api.dto.LoginDto;
import com.cheng.api.dto.SysUserDto;
import com.cheng.api.dto.UserDto;
import com.cheng.api.entity.Member;
import com.cheng.api.entity.User;
import com.cheng.api.exception.CustomException;
import com.cheng.api.mapper.MemberMapper;
import com.cheng.api.mapper.UserMapper;
import com.cheng.api.service.impl.MobileValidateCodeServiceImpl;
import com.cheng.api.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Objects;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author rdg
 * @since 2024-06-19
 */
@RestController
public class UserController extends BaseController {

    @Autowired
    UserMapper userMapper;

    @Autowired
    MemberMapper memberMapper;

    @Autowired
    MobileValidateCodeServiceImpl mobileValidateCodeService;

    @PostMapping("/user/login")
    public R<HashMap<String,Object>> login(@RequestBody LoginDto loginDto){
        User user = userMapper.selectOne(Wrappers.lambdaQuery(User.class)
                .eq(User::getMobile, loginDto.getMobile())
                .eq(User::getPassword, loginDto.getPassword())
                .last("limit 1"));
        if (user == null){
            throw new CustomException(401,"用户名或密码错误");
        }else{
            Member member = memberMapper.selectOne(Wrappers.lambdaQuery(Member.class)
                    .eq(Member::getUserId, user.getId())
                    .last("limit 1"));
            SysUserDto sysUserDto = new SysUserDto(user);
            sysUserDto.setMember(member);
            Integer memberId = Objects.isNull(member) ? null : member.getId();
            String token = JwtUtil.generateToken(user.getId(),user.getName(),memberId,member.getSex());

            HashMap<String,Object> map = new HashMap<>();
            map.put("userInfo",sysUserDto);
            map.put("accessToken",token);
            map.put("tokenType","bearer");
            return R.success(map);
        }
    }

    @PostMapping("/user/register")
    public R<?> register(@RequestBody UserDto userDto){

        String mobile = userDto.getMobile();
        String password = userDto.getPassword();
        String passwordConfirmation = userDto.getPasswordConfirmation();

        if (StringUtils.isBlank(password) || StringUtils.isBlank(passwordConfirmation)){
            throw new CustomException(422,"请输入密码");
        }

        if (!password.equals(passwordConfirmation)){
            throw new CustomException(422,"两次输入密码不一致");
        }

        Boolean aBoolean = mobileValidateCodeService.verifyValidateCode(userDto.getMobile(), userDto.getCode());
        if (!aBoolean){
            throw new CustomException("短信验证失败,请重试");
        }

        User existUser = userMapper.selectOne(Wrappers.lambdaQuery(User.class)
                .eq(User::getMobile, userDto.getMobile())
                .last("limit 1"));
        if (existUser != null){
            throw new CustomException(422,"该手机号已注册");
        }else{
            User user = new User(userDto);
            user.setName(userDto.getMobile());
            int insert = userMapper.insert(user);
            Member member = new Member();
            member.setUserId(user.getId());
            memberMapper.insert(member);

            if (insert > 0){
                return R.success(null);
            }else{
                return R.error("注册失败,请稍后重试");
            }
        }
    }

    @PostMapping("/user/logout")
    public R<?> register(){
        //将 token 加入到 token blackList 中,每次登录中间件检测过滤在blackList中的 token
        return R.success(null);
    }
}

