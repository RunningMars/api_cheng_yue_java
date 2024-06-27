package com.cheng.api.config;

import com.cheng.api.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {

    @Autowired
    LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                //.allowedOriginPatterns("http://localhost:*")
                //允许直接访问的路径
                .excludePathPatterns(
                    "/error",
                    "/index",
                    "/uploadImage",
                    "/user/login",
                    "/user/register",
                    "/user/logout",
                    "/sms/validate/send",
                    "/webjars/**",
                    "/swagger-ui/**",
                    "/swagger-ui.html",
                    "/doc.html",
                    "/swagger-resources/**"
                );
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("http://cheng_yue.com", "http://localhost:*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
