package com.cheng.api;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.SimpleQuery;
import com.cheng.api.common.R;
import com.cheng.api.entity.Member;
import com.cheng.api.entity.MemberImage;
import com.cheng.api.mapper.MemberImageMapper;
import com.cheng.api.mapper.MemberMapper;
import com.cheng.api.service.MemberImageServiceI;
import com.cheng.api.service.MemberServiceI;
import com.cheng.api.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
class ApiApplicationTests {

    @Autowired
    private MemberServiceI memberService;


    @Autowired
    private MemberMapper memberMapper;


    @Autowired
    private MemberImageServiceI memberImageService;


    @Autowired
    private MemberImageMapper memberImageMapper;


    @Test
    public void memberList(){
        Page<Member> page = new Page<>(1,5);
        QueryWrapper<Member> memberQueryWrapper = new QueryWrapper<>();
        memberQueryWrapper.eq("id",20001L);
        Page<Member> page1 = memberService.page(page, memberQueryWrapper);

        System.out.println(R.success(page1));
    }

    @Test
    public void testMemberImage(){
        HashMap<String,Object> map = new HashMap<>();
        map.put("member_id",20001L);

        List<MemberImage> memberImage = memberImageMapper.selectByMap(map);

        memberImage.forEach(System.out::println);
    }

    @Test
    public void test0(){
//        Member member = memberMapper.selectMemberWithImages(20001);
//        System.out.println(member);

    }

    @Test
    public void test(){
        Long count = memberService.count(null);
        System.out.println(count);
    }


    @Test
    public void test2(){
        // 假设有一个 Member 实体类和对应的 BaseMapper
        LambdaQueryWrapper<Member> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.isNotNull(Member::getSex); //

        // 使用 group 方法查询并封装结果，按照用户名分组
//        Map<String, List<Member>> memberGroup = SimpleQuery.group(
//            queryWrapper, // 查询条件构造器
//            Member::getSex, // 使用用户名作为分组键
//            member -> System.out.println("Processing member: " + member.getRealName()) // 打印处理的用户名
//        );

        // 遍历结果
//        for (Map.Entry<String, List<Member>> entry : memberGroup.entrySet()) {
//            System.out.println("MemberSex: " + entry.getKey());
//            for (Member member : entry.getValue()) {
//                System.out.println(" - Member: " + member);
//            }
//        }
    }

    @Test
    void contextLoads() {
    }

    @Test
    void selectList() {
        List<Member> members = memberMapper.selectList(null);
        members.forEach(System.out::println);
    }

    @Test
    void updateById() {
        Member member = new Member();
        member.setId(20001);
        member.setEmail("test222@email");
        int i = memberMapper.updateById(member);
        System.out.println(i);
    }

    @Test
    void insert() {
        Member member = new Member();
        member.setRealName("test222");
        int insert = memberMapper.insert(member);
        System.out.println(insert);
    }

    @Test
    void selectByBatchIds() {
        List<Member> members = memberMapper.selectBatchIds(Arrays.asList(20001L,20002L,20003L));
        members.forEach(System.out::println);
    }

    @Test
    void selectByMap() {
        HashMap<String, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("age",28);
        List<Member> members = memberMapper.selectByMap(objectObjectHashMap);
        members.forEach(System.out::println);
    }

    @Test//测试分页查询
    public void testPage(){
        //参数一current：当前页   参数二size：页面大小
        //使用了分页插件之后，所有的分页操作都变得简单了
        Page<Member> objectPage = new Page<>(1, 5);
        memberMapper.selectPage(objectPage,null);
        objectPage.getRecords().forEach(System.out::println);
        System.out.println("总页数==>" + objectPage.getTotal());
    }

    @Test
    public void deleteByMap(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("id",20016L);
        memberMapper.deleteByMap(map);
    }


    @Test
    public void wrapper(){
        QueryWrapper<Member> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.eq("email","test222@email");
        List<Member> members = memberMapper.selectList(objectQueryWrapper);
        members.forEach(System.out::println);
    }

    @Test
    public void wrapper2(){
        QueryWrapper<Member> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.eq("email","test222@email");
        Long member = memberMapper.selectCount(objectQueryWrapper);
        System.out.println(member);
    }

    @Test
    void queryAllUser3() {
        String name = "test222";
        Integer age = null;
        String mobile = null;

        QueryWrapper<Member> wrapper = new QueryWrapper<>();

        wrapper.eq(StringUtils.isNotBlank(name),"real_name",name)
                .eq(age != null && age > 0 , "age" ,age)
                .eq(StringUtils.isNotBlank(mobile),"mobile",mobile);

        List<Member> members = memberMapper.selectList(wrapper);

        members.forEach(System.out::println);
    }


//import au.com.koalaclass.session.util.JwtUtils;
//import io.jsonwebtoken.Claims;
//import org.junit.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//import java.util.Base64;

    @Test
    public void test23() throws Exception {
//        String smsSignName = ConfigUtil.getProperty("ALI_YUN_SMS_SIGN_NAME");
//        System.out.println(smsSignName);
//        SmsUtil.sendValidateSms("13666288348","1469");

        List list = Member.annualIncomeMap.get("5w-10w");
        System.out.println(list);
        System.out.println(Collections.min(list).getClass());
        System.out.println(Collections.max(list));
//        member.getAnnualIncomeMin(Collections.min(list));
//        member.getAnnualIncomeMax(Collections.max(list));

    }

    /**
     * @Author : lzy
     * @CreateTime : 2021/6/18
     * @Description :
     **/
    @Test
    public void test1() {

        // 生成token
//        String s = JwtUtil.generateToken(199,"rdg",20003);
//        System.out.println(s);
//        // 验证
//        Claims claims = JwtUtil.verifyJwt(s);
//        System.out.println("claims:"+claims);
//        String subject = claims.getSubject();
//        String userId = claims.get("userId").toString();
//        String name =  claims.get("name").toString();
//        String sub = claims.get("sub").toString();
//        System.out.println("subject:" + subject);
//        System.out.println("userId:" + userId);
//        System.out.println("name:" + name);
//        System.out.println("sub:" + sub);
    }


}
