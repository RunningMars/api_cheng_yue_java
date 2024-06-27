package com.cheng.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cheng.api.dto.ChatRoomDto;
import com.cheng.api.entity.ChatRoom;
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
public interface ChatRoomMapper extends BaseMapper<ChatRoom> {
    public Page<ChatRoomDto> chatRoomList(Page<ChatRoomDto> page, @Param("memberId") Integer memberId , @Param("keyWord") String keyWord);


}
