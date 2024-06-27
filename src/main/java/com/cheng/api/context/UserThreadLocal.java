package com.cheng.api.context;

import com.cheng.api.dto.SysUserDto;
import com.cheng.api.entity.Member;

import java.util.Optional;

/**
 * 用于存储用户上下文信息
 */
public class UserThreadLocal {

    private static final ThreadLocal<SysUserDto> USER_INFO_THREAD_LOCAL = new ThreadLocal<>();

    private UserThreadLocal() { }

    public static void clear() {
        USER_INFO_THREAD_LOCAL.remove();
    }

    public static void set(SysUserDto userDto) {
        USER_INFO_THREAD_LOCAL.set(userDto);
    }

    public static SysUserDto getCurrentUser() {
        return USER_INFO_THREAD_LOCAL.get();
    }

    public static Integer getCurrentMemberId() {
        SysUserDto currentUser = USER_INFO_THREAD_LOCAL.get();
        // 使用 Optional 来避免深层次的 null 检查
        Optional<Integer> currentMemberId = Optional.ofNullable(currentUser)
                .map(SysUserDto::getMember)
                .map(Member::getId);
        if (currentMemberId.isEmpty()){
            System.out.println("memberId.isEmpty()");
            return null;
        }
        return currentMemberId.get();
    }


}
