package com.cheng.api.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cheng.api.mapper.MemberImageMapper;
import com.cheng.api.entity.MemberImage;
import com.cheng.api.service.MemberImageServiceI;
import org.springframework.stereotype.Service;

@Service
public class MemberImageServiceImpl extends ServiceImpl<MemberImageMapper, MemberImage> implements MemberImageServiceI {

}
