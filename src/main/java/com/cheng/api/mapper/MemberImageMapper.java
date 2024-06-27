package com.cheng.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cheng.api.entity.MemberImage;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberImageMapper extends BaseMapper<MemberImage> {

//    @Select("select * from member_image where member_id = #{memberId}")
//    List<MemberImage> getByMemberId(Integer memberId);
}
