package com.cheng.api.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cheng.api.entity.SendSms;
import com.cheng.api.mapper.SendSmsMapper;
import com.cheng.api.service.SendSmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author rdg
 * @since 2024-06-26
 */
@Service
public class SendSmsServiceImpl extends ServiceImpl<SendSmsMapper, SendSms> implements SendSmsService {

    @Autowired
    SendSmsMapper sendSmsMapper;

}
