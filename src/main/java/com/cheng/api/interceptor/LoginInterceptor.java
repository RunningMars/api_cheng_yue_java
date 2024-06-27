package com.cheng.api.interceptor;

import com.cheng.api.context.UserThreadLocal;
import com.cheng.api.dto.SysUserDto;
import com.cheng.api.entity.Member;
import com.cheng.api.exception.CustomException;
import com.cheng.api.util.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 逻辑
        log.info("来到 LoginInterceptor ..");
        String requestURL = request.getRequestURL().toString();
        System.out.println(requestURL);

        String authorization = request.getHeader("Authorization");
        if (authorization == null){
            throw new CustomException(401,"没有身份令牌token");
        }

        Claims claims = JwtUtil.verifyJwtWithBearer(authorization);

        if (claims == null){
            throw new CustomException(401,"token失效,请重新登录");
        }else{
            SysUserDto sysUserDto = new SysUserDto();
            Object userId = claims.get("userId");
            Object sex = claims.get("sex");
            sysUserDto.setId((Integer)userId);
            sysUserDto.setName(claims.get("name").toString());
            Object memberId = claims.get("memberId");
            Member member = new Member((Integer) memberId);
            member.setSex((Integer)sex);
            sysUserDto.setMember(member);
            UserThreadLocal.set(sysUserDto);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
