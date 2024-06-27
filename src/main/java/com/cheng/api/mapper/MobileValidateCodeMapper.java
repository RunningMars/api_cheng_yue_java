package com.cheng.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cheng.api.entity.MobileValidateCode;
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
public interface MobileValidateCodeMapper extends BaseMapper<MobileValidateCode> {

}
