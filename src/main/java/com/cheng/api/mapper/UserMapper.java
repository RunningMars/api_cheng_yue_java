package com.cheng.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cheng.api.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author rdg
 * @since 2024-06-19
 */
@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {

}
