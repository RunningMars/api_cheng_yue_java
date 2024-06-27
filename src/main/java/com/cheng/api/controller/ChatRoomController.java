package com.cheng.api.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cheng.api.common.R;
import com.cheng.api.context.UserThreadLocal;
import com.cheng.api.dto.ChatRoomDto;
import com.cheng.api.dto.SysUserDto;
import com.cheng.api.dto.query.ChatRoomMessageQuery;
import com.cheng.api.service.impl.ChatRoomServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author rdg
 * @since 2024-06-21
 */
@RestController
public class ChatRoomController extends BaseController {

    @Autowired
    ChatRoomServiceImpl chatRoomService;

    @RequestMapping("/message/chat/list")
    public R<Page<ChatRoomDto>> chatRoomList(@RequestParam HashMap<String, Object> params){
        System.out.println("params:"+params);
        Page<ChatRoomDto> res = chatRoomService.chatRoomList(params);
        return R.success(res);
    }

    @RequestMapping("/message/chat/unread_count")
    public R<HashMap<String, Object>> unreadChatCount(){
        SysUserDto currentUser = UserThreadLocal.getCurrentUser();
        System.out.println(currentUser);
        HashMap<String, Object> stringObjectHashMap = chatRoomService.unreadChatCount(currentUser.getMember().getId());
        return R.success(stringObjectHashMap);
    }

    @RequestMapping("/message/chat/message")
    public R<HashMap<String, Object>> getChatRoomMessage(@ModelAttribute ChatRoomMessageQuery chatRoomMessageQuery){
        SysUserDto currentUser = UserThreadLocal.getCurrentUser();
        System.out.println(currentUser);
        HashMap<String, Object> stringObjectHashMap = chatRoomService.getChatRoomMessage(chatRoomMessageQuery);
        return R.success(stringObjectHashMap);
    }

}

