package com.cheng.api.controller;


import com.cheng.api.common.R;
import com.cheng.api.dto.query.SendMessageQuery;
import com.cheng.api.service.impl.ChatRoomMessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
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
public class ChatRoomMessageController extends BaseController {

    @Autowired
    ChatRoomMessageServiceImpl chatRoomMessageService;

    @RequestMapping("/message/chat/send")
    public R<?> sendMessage(@RequestBody SendMessageQuery sendMessageQuery){
        Boolean aBoolean = chatRoomMessageService.sendMessage(sendMessageQuery);

        if (!aBoolean){
            return R.error("消息发送失败");
        }

        return R.success(null);
    }
}

