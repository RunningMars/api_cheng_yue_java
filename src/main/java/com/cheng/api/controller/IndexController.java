package com.cheng.api.controller;

import com.cheng.api.util.ConfigUtil;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Controller;

@Controller
public class IndexController {

    @Test
    public void test() throws Exception {
        System.out.println("well done ");

        String ali_yun_access_key_id = ConfigUtil.getProperty("ALI_YUN_ACCESS_KEY_ID");
        System.out.println("ali_yun_access_key_id"+ali_yun_access_key_id);
        //SmsUtil.sendSms();
        System.out.println("well done finish");
    }


    public void test2(){
        System.out.println("well done!");
    }
}
