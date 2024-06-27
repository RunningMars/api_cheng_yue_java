package com.cheng.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cheng.api.dto.ChatRoomMemberDto;
import com.cheng.api.entity.ChatRoomMember;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

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
public interface ChatRoomMemberMapper extends BaseMapper<ChatRoomMember> {

    public List<ChatRoomMemberDto> selectChatRoomMember(Integer memberId, Set<Integer> chatRoomIds);

    public List<ChatRoomMember> selectChatRoomMeMember(Integer memberId, Set<Integer> chatRoomIds);
}
