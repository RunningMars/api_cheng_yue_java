package com.cheng.api.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cheng.api.common.R;
import com.cheng.api.context.UserThreadLocal;
import com.cheng.api.dto.MemberDto;
import com.cheng.api.dto.MemberQuery;
import com.cheng.api.dto.query.EditMemberQuery;
import com.cheng.api.entity.Member;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Slf4j
@RestController //表示controller所有返回都让 srping 转换为 json 格式
@RequestMapping("/member")
@Api("会员模块API")
public class MemberController extends BaseController {


    @RequestMapping("/list")
    @ApiOperation(value = "会员列表", notes = "展示会员")
    public R<Page<MemberDto>> memberList(@ModelAttribute MemberQuery memberQuery){

        Page<MemberDto>  pageResult = memberService.memberList(memberQuery);

        return R.success(pageResult);
    }


    @PostMapping("/list2")
    @ApiOperation(value = "会员列表2", notes = "展示会员")
    public R<List<Member>> memberList2(){
        //System.out.println(memberQuery);

//        LambdaQueryWrapper<Member> memberQueryWrapper = new LambdaQueryWrapper<>();
//        memberQueryWrapper.eq(StringUtils.isNotBlank(memberQuery.getRealName()),Member::getRealName,memberQuery.getRealName());
        List<Member> list = memberService.list(null);

        System.out.println("9990");
        return R.success(list);
    }

    @GetMapping("/detail")
    @ResponseBody
    @ApiOperation("会员详情")
    public R<MemberDto> detail(@RequestParam Integer id){

        System.out.println(id);

        if (id == null){ return R.notFound("不存在该用户"); }

        MemberDto m = memberService.memberDetail(id);
        if (m == null){
            return R.notFound("不存在该用户");
        }

        return R.success(m);
    }

    @PostMapping("/add")
    @ResponseBody
    @ApiOperation(value = "add", notes = "")
    public String add(@Validated @RequestBody Member member){
        Date date = new Date();
        System.out.println(date);
        return "334567";
    }

    @GetMapping("/test")
    @ResponseBody
    @ApiOperation(value = "test", notes = "用于测试的方法")
    public String test2(){
        Date date = new Date();
        System.out.println(date);
        return "334567";
    }

    public void test3(){
        System.out.println(123);
    }


    @RequestMapping("/save")
    @ResponseBody
    @ApiOperation(value = "编辑会员", notes = "编辑会员")
    public R<?> saveMember(@RequestBody EditMemberQuery editMemberQuery){
        Integer currentMemberId = UserThreadLocal.getCurrentMemberId();
        if (currentMemberId == null){
            return R.error("操作失败");
        }

        Boolean aBoolean = memberService.saveMember(editMemberQuery);

        if (!aBoolean){
            return R.error("操作失败");
        }

        return R.success(null);
    }

}
