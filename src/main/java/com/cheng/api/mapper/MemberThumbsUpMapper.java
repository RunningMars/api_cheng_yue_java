package com.cheng.api.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cheng.api.dto.MemberThumbsUpDto;
import com.cheng.api.entity.MemberThumbsUp;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author rdg
 * @since 2024-06-25
 */
@Mapper
@Repository
public interface MemberThumbsUpMapper extends BaseMapper<MemberThumbsUp> {

    public Page<MemberThumbsUpDto> searchThumbsUpList(Page<MemberThumbsUp> page, @Param("currentMemberId") Integer currentMemberId, @Param("keyWord") String keyWord);

}
