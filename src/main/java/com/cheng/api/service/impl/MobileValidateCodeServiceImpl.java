package com.cheng.api.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cheng.api.entity.MobileValidateCode;
import com.cheng.api.entity.SendSms;
import com.cheng.api.exception.CustomException;
import com.cheng.api.mapper.MobileValidateCodeMapper;
import com.cheng.api.service.MobileValidateCodeService;
import com.cheng.api.util.SmsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Random;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author rdg
 * @since 2024-06-26
 */
@Service
public class MobileValidateCodeServiceImpl extends ServiceImpl<MobileValidateCodeMapper, MobileValidateCode> implements MobileValidateCodeService {

    @Autowired
    MobileValidateCodeMapper mobileValidateCodeMapper;

    @Autowired
    SendSmsServiceImpl sendSmsService;

    public Boolean sendValidateSms(String mobile) throws Exception {
        // 获取当前时间
        LocalDateTime now = LocalDateTime.now();
        // 减去60秒
        LocalDateTime timeBefore60Seconds = now.minusSeconds(60);
        // 定义日期时间格式  格式化日期时间
        String formattedTime = timeBefore60Seconds.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        MobileValidateCode mobileValidateCode = mobileValidateCodeMapper.selectOne(Wrappers.lambdaQuery(MobileValidateCode.class)
                .eq(MobileValidateCode::getMobile, mobile)
                .ge(MobileValidateCode::getCreatedAt, formattedTime)
                .last("limit 1"));
        if (mobileValidateCode != null){
            throw new CustomException("请稍后重试,重新发送短信验证码.");
        }

        Random random = new Random();
        int code = 1000 + random.nextInt(9000);
        String stringCode = String.valueOf(code);
        SendSms sendSms = SmsUtil.sendValidateSms(mobile, stringCode);
        if (!Objects.isNull(sendSms)){
            sendSmsService.save(sendSms);
            mobileValidateCodeMapper.delete(Wrappers.lambdaQuery(MobileValidateCode.class)
                    .eq(MobileValidateCode::getMobile, mobile)
                    .le(MobileValidateCode::getCreatedAt, now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))) );

            MobileValidateCode mobileValidateCodeNew = new MobileValidateCode();
            mobileValidateCodeNew.setMobile(mobile);
            mobileValidateCodeNew.setCode(stringCode);
            mobileValidateCodeMapper.insert(mobileValidateCodeNew);
            return true;
        }

        return false;
    }

    public Boolean verifyValidateCode(String mobile, String code){
        // 获取当前时间
        LocalDateTime now = LocalDateTime.now();
        // 减去600秒
        LocalDateTime timeBefore600Seconds = now.minusSeconds(600);
        // 定义日期时间格式  格式化日期时间
        String formattedTime = timeBefore600Seconds.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        MobileValidateCode mobileValidateCode = mobileValidateCodeMapper.selectOne(Wrappers.lambdaQuery(MobileValidateCode.class)
                .eq(MobileValidateCode::getMobile, mobile)
                .eq(MobileValidateCode::getCode, code)
                .ge(MobileValidateCode::getCreatedAt, formattedTime)
                .last("limit 1"));
        if (mobileValidateCode != null){
            return true;
        }
        return false;
    }
}
