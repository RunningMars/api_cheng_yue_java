package com.cheng.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cheng.api.dto.ChatRoomMessageDto;
import com.cheng.api.entity.ChatRoomMessage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author rdg
 * @since 2024-06-21
 */
@Mapper
@Repository
public interface ChatRoomMessageMapper extends BaseMapper<ChatRoomMessage> {

    public ChatRoomMessageDto getChatRoomLastMessage(@Param("chatRoomId") Integer chatRoomId);

    public Page<ChatRoomMessageDto> getMessageByChatRoomId(Page<ChatRoomMessage> page, @Param("chatRoomId") Integer chatRoomId);
}
