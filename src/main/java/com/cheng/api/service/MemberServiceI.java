package com.cheng.api.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cheng.api.dto.MemberDto;
import com.cheng.api.dto.MemberQuery;
import com.cheng.api.dto.query.EditMemberQuery;
import com.cheng.api.entity.Member;

public interface MemberServiceI extends IService<Member> {

    Page<MemberDto> memberList(MemberQuery memberQuery);

    MemberDto memberDetail(Integer id);

    Boolean saveMember(EditMemberQuery editMemberQuery);
}
