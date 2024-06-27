package com.cheng.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cheng.api.entity.Member;
import com.cheng.api.entity.MemberImage;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper //告诉springboot 这是 mybatis 的 mapper 类
@Repository //将MemberMapper类交给 springboot 容器管理
public interface MemberMapper extends BaseMapper<Member> {

    @Select("SELECT * FROM member WHERE id = #{id}")
    @Results({
            @Result(property = "memberImages", column = "id",
                    many = @Many(select = "getByMemberId"))
    })
    Member selectMemberWithImages(Integer id);

    @Select("SELECT * FROM member_image WHERE member_id = #{id}")
    List<MemberImage> getByMemberId(Integer id);


//    @Insert("insert into member (name,age,job) values (#{name},#{age},#{job})")
//    int addMember(MemberEntity member);

    //    @Delete("delete from member where id = #{id}")
//    int delMember(int id);
//
//    @Update("update  member set name = #{name}, age = #{age},job = #{job} where id = #{id}")
//    int updateMember(MemberEntity member);
//
//    @Select("select * from member")
    //List<Member> selectAllUser(MemberQuery memberQuery);

    //HashMap selectUserById(Integer id);

//    @Select("select * from member where id = #{id}")
//    MemberEntity getMember(int id);

}
