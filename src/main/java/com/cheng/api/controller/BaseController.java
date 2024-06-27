package com.cheng.api.controller;

import com.cheng.api.service.MemberServiceI;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseController {

    @Autowired
    MemberServiceI memberService;
}
