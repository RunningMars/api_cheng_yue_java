package com.cheng.api.mapper;

import com.cheng.api.entity.SendSms;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author rdg
 * @since 2024-06-26
 */
@Mapper
@Repository
public interface SendSmsMapper extends BaseMapper<SendSms> {

}
