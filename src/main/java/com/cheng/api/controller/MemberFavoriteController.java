package com.cheng.api.controller;


import com.cheng.api.common.R;
import com.cheng.api.service.impl.MemberFavoriteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author rdg
 * @since 2024-06-25
 */

@RestController
public class MemberFavoriteController extends BaseController {


    @Autowired
    MemberFavoriteServiceImpl memberFavoriteService;

    @RequestMapping("/member/favorite/update")
    public R<?> updateMyFavorite(@RequestParam(required = true) Integer toMemberId, @RequestParam (required = true) Integer isFavorite){

        System.out.println(toMemberId);
        System.out.println(isFavorite);
        Boolean aBoolean = memberFavoriteService.updateMyFavorite(toMemberId, isFavorite);

        if (!aBoolean){
            return R.error("收藏失败");
        }

        return R.success(null);
    }
}

