package com.cheng.api.controller;


import com.cheng.api.common.R;
import com.cheng.api.service.impl.ChatRoomMemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author rdg
 * @since 2024-06-21
 */
@RestController
public class ChatRoomMemberController extends BaseController {

    @Autowired
    ChatRoomMemberServiceImpl chatRoomMemberService;

    @RequestMapping("/message/chat/read_all")
    public R<?> readAll(){
        Boolean aBoolean = chatRoomMemberService.readAll();

        if (!aBoolean){
            return R.error("操作失败");
        }

        return R.success(null);
    }
}

