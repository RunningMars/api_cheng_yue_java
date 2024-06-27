package com.cheng.api.controller;


import com.cheng.api.common.R;
import com.cheng.api.service.impl.MemberThumbsUpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author rdg
 * @since 2024-06-25
 */
@RestController
public class MemberThumbsUpController extends BaseController {


    @Autowired
    MemberThumbsUpServiceImpl memberThumbsUpService;

    @RequestMapping("/member/thumbs_up/update")
    public R<?> updateMyThumbsUp(@RequestParam(required = true) Integer toMemberId, @RequestParam (required = true) Integer isThumbsUp){

        System.out.println(toMemberId);
        System.out.println(isThumbsUp);
        Boolean aBoolean = memberThumbsUpService.updateMyThumbsUp(toMemberId, isThumbsUp);

        if (!aBoolean){
            return R.error("点赞失败");
        }

        return R.success(null);
    }

    @RequestMapping("/member/thumbs_up/list")
    public R<HashMap<String, Object>> receiveMyThumbsUpList(@RequestParam(required = false) String keyWord, @RequestParam(required = false) Integer pageNum, @RequestParam(required = false) Integer pageSize){

        System.out.println(keyWord);

        HashMap<String, Object> res = memberThumbsUpService.receiveMyThumbsUpList(keyWord, pageNum, pageSize);

        return R.success(res);
    }
}

