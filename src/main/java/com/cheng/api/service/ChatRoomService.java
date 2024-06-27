package com.cheng.api.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cheng.api.dto.ChatRoomDto;
import com.cheng.api.entity.ChatRoom;

import java.util.HashMap;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author rdg
 * @since 2024-06-21
 */
public interface ChatRoomService extends IService<ChatRoom> {

    public Page<ChatRoomDto> chatRoomList(HashMap<String,Object> requestMap);

    public HashMap<String, Object> unreadChatCount(Integer id);

}
