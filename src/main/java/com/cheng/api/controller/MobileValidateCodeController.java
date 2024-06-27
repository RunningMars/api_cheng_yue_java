package com.cheng.api.controller;


import com.cheng.api.common.R;
import com.cheng.api.dto.query.SendValidateCodeQuery;
import com.cheng.api.service.impl.MobileValidateCodeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author rdg
 * @since 2024-06-26
 */
@RestController
public class MobileValidateCodeController extends BaseController {

    @Autowired
    MobileValidateCodeServiceImpl mobileValidateCodeService;

    @RequestMapping("sms/validate/send")
    public R<?> sendValidateCode(@RequestBody SendValidateCodeQuery sendValidateCodeQuery) throws Exception {

        Boolean aBoolean = mobileValidateCodeService.sendValidateSms(sendValidateCodeQuery.getMobile());
        if (!aBoolean){
            return R.error();
        }
        return R.success(null);
    }
}

