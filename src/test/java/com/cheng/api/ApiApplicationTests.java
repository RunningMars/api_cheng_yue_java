package com.cheng.api;

import com.cheng.api.mapper.MemberImageMapper;
import com.cheng.api.mapper.MemberMapper;
import com.cheng.api.service.MemberImageServiceI;
import com.cheng.api.service.MemberServiceI;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
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


//    @Test
//    public void memberList(){
//        Page<Member> page = new Page<>(1,5);
//        QueryWrapper<Member> memberQueryWrapper = new QueryWrapper<>();
//        memberQueryWrapper.eq("id",20001L);
//        Page<Member> page1 = memberService.page(page, memberQueryWrapper);
//
//        System.out.println(R.success(page1));
//    }
//
//    @Test
//    public void testMemberImage(){
//        HashMap<String,Object> map = new HashMap<>();
//        map.put("member_id",20001L);
//
//        List<MemberImage> memberImage = memberImageMapper.selectByMap(map);
//
//        memberImage.forEach(System.out::println);
//    }
//
//    @Test
//    public void test0(){
////        Member member = memberMapper.selectMemberWithImages(20001);
////        System.out.println(member);
//
//    }
//
//    @Test
//    public void test(){
//        Long count = memberService.count(null);
//        System.out.println(count);
//    }
//
//
//    @Test
//    public void test2(){
//        // 假设有一个 Member 实体类和对应的 BaseMapper
//        LambdaQueryWrapper<Member> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.isNotNull(Member::getSex); //
//
//        // 使用 group 方法查询并封装结果，按照用户名分组
////        Map<String, List<Member>> memberGroup = SimpleQuery.group(
////            queryWrapper, // 查询条件构造器
////            Member::getSex, // 使用用户名作为分组键
////            member -> System.out.println("Processing member: " + member.getRealName()) // 打印处理的用户名
////        );
//
//        // 遍历结果
////        for (Map.Entry<String, List<Member>> entry : memberGroup.entrySet()) {
////            System.out.println("MemberSex: " + entry.getKey());
////            for (Member member : entry.getValue()) {
////                System.out.println(" - Member: " + member);
////            }
////        }
//    }
//
//    @Test
//    void contextLoads() {
//    }
//
//    @Test
//    void selectList() {
//        List<Member> members = memberMapper.selectList(null);
//        members.forEach(System.out::println);
//    }
//
//    @Test
//    void updateById() {
//        Member member = new Member();
//        member.setId(20001);
//        member.setEmail("test222@email");
//        int i = memberMapper.updateById(member);
//        System.out.println(i);
//    }
//
//    @Test
//    void insert() {
//        Member member = new Member();
//        member.setRealName("test222");
//        int insert = memberMapper.insert(member);
//        System.out.println(insert);
//    }
//
//    @Test
//    void selectByBatchIds() {
//        List<Member> members = memberMapper.selectBatchIds(Arrays.asList(20001L,20002L,20003L));
//        members.forEach(System.out::println);
//    }
//
//    @Test
//    void selectByMap() {
//        HashMap<String, Object> objectObjectHashMap = new HashMap<>();
//        objectObjectHashMap.put("age",28);
//        List<Member> members = memberMapper.selectByMap(objectObjectHashMap);
//        members.forEach(System.out::println);
//    }
//
//    @Test//测试分页查询
//    public void testPage(){
//        //参数一current：当前页   参数二size：页面大小
//        //使用了分页插件之后，所有的分页操作都变得简单了
//        Page<Member> objectPage = new Page<>(1, 5);
//        memberMapper.selectPage(objectPage,null);
//        objectPage.getRecords().forEach(System.out::println);
//        System.out.println("总页数==>" + objectPage.getTotal());
//    }
//
//    @Test
//    public void deleteByMap(){
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("id",20016L);
//        memberMapper.deleteByMap(map);
//    }
//
//
//    @Test
//    public void wrapper(){
//        QueryWrapper<Member> objectQueryWrapper = new QueryWrapper<>();
//        objectQueryWrapper.eq("email","test222@email");
//        List<Member> members = memberMapper.selectList(objectQueryWrapper);
//        members.forEach(System.out::println);
//    }
//
//    @Test
//    public void wrapper2(){
//        QueryWrapper<Member> objectQueryWrapper = new QueryWrapper<>();
//        objectQueryWrapper.eq("email","test222@email");
//        Long member = memberMapper.selectCount(objectQueryWrapper);
//        System.out.println(member);
//    }
//
//    @Test
//    void queryAllUser3() {
//        String name = "test222";
//        Integer age = null;
//        String mobile = null;
//
//        QueryWrapper<Member> wrapper = new QueryWrapper<>();
//
//        wrapper.eq(StringUtils.isNotBlank(name),"real_name",name)
//                .eq(age != null && age > 0 , "age" ,age)
//                .eq(StringUtils.isNotBlank(mobile),"mobile",mobile);
//
//        List<Member> members = memberMapper.selectList(wrapper);
//
//        members.forEach(System.out::println);
//    }
//
//
////import au.com.koalaclass.session.util.JwtUtils;
////import io.jsonwebtoken.Claims;
////import org.junit.Test;
////import org.springframework.boot.test.context.SpringBootTest;
////import java.util.Base64;
//
//    @Test
//    public void test23() throws Exception {
////        String smsSignName = ConfigUtil.getProperty("ALI_YUN_SMS_SIGN_NAME");
////        System.out.println(smsSignName);
////        SmsUtil.sendValidateSms("13666288348","1469");
//
//        List list = Member.annualIncomeMap.get("5w-10w");
//        System.out.println(list);
//        System.out.println(Collections.min(list).getClass());
//        System.out.println(Collections.max(list));
////        member.getAnnualIncomeMin(Collections.min(list));
////        member.getAnnualIncomeMax(Collections.max(list));
//
//    }
//
//    /**
//     * @Author : lzy
//     * @CreateTime : 2021/6/18
//     * @Description :
//     **/
//    @Test
//    public void test1() {
//
//        // 生成token
////        String s = JwtUtil.generateToken(199,"rdg",20003);
////        System.out.println(s);
////        // 验证
////        Claims claims = JwtUtil.verifyJwt(s);
////        System.out.println("claims:"+claims);
////        String subject = claims.getSubject();
////        String userId = claims.get("userId").toString();
////        String name =  claims.get("name").toString();
////        String sub = claims.get("sub").toString();
////        System.out.println("subject:" + subject);
////        System.out.println("userId:" + userId);
////        System.out.println("name:" + name);
////        System.out.println("sub:" + sub);
//    }



    private static final int SPLIT_LENGTH = 244;
    private static final String PUBLIC_KEY_PEM = "-----BEGIN PUBLIC KEY-----\n" +
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA1WGg5oL31tLGzq8r9qVY\n" +
            "oKZKIMv6SIJSTajVWren7gffEf43+Ua4E8oBC1rBarzfUt79M75zBMYhburkJ6X/\n" +
            "5k4dEwnW/h8UB2ZM00nDIp77tEidDDgr3nDQfInwV9AakRq5jgEA7hj7r/MH2mRW\n" +
            "975P1t0YTSrJttQb1is9LTkiIeXiz+qeGZe4E7Jt/gk3aSey1XYW4rwUakdFsnC1\n" +
            "td0UyvxyJiWSQTfU5Bs4Bh6P+4lZCC/XZQ447Bb2UoJZjt/B3o9VJMGjXhdkntzL\n" +
            "t1Ez+TIeKe6M2boGPbswF8uIQBcpgxnB9PNAAoZTijGmWgv5NvlDxDnRqTiUJulF\n" +
            "qwIDAQAB\n" +
            "-----END PUBLIC KEY-----";

    public String encrypt(String rawString) throws Exception {
        PublicKey publicKey = loadPublicKey(PUBLIC_KEY_PEM);
        List<String> rawArr = splitString(rawString, SPLIT_LENGTH);
        List<String> encryptArr = new ArrayList<>();

        for (String raw : rawArr) {
            byte[] encryptedBytes = encryptWithPublicKey(raw.getBytes(StandardCharsets.UTF_8), publicKey);
            encryptArr.add(Base64.getEncoder().encodeToString(encryptedBytes));
        }

        return String.join("::", encryptArr);
    }

    private PublicKey loadPublicKey(String publicKeyPEM) throws Exception {
        String publicKeyPEMFormatted = publicKeyPEM
                .replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s+", "");

        byte[] encoded = Base64.getDecoder().decode(publicKeyPEMFormatted);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(encoded);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(keySpec);
    }

    private List<String> splitString(String input, int length) {
        List<String> parts = new ArrayList<>();
        int index = 0;
        while (index < input.length()) {
            parts.add(input.substring(index, Math.min(index + length, input.length())));
            index += length;
        }
        return parts;
    }

    private byte[] encryptWithPublicKey(byte[] data, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(data);
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public String generateSignature(String appId, String appKey, long timestamp, String nonce) throws Exception {
        String originalString = appId + appKey + timestamp + nonce;
        System.out.println("originalString");
        System.out.println(originalString);
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(originalString.getBytes());
            String sha256hex = bytesToHex(encodedhash);
            return sha256hex;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Test
    public void testRequest() {
        try {
            HashMap<String,Object> parameters = new HashMap<String,Object>();
            parameters.put("EMDM_location_id","2bfd5f85-abb1-41e1-920e-f69374604587");
            ArrayList<String> strings = new ArrayList<>();
            strings.add("HV111");
            parameters.put("number", strings);

            String encryptedRequestString = encrypt(new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(parameters));
            String appId = "DKMJwBzQ6uH9hEgxJLWT";
            String appKey = "HDfOr6RFt9ZTPJc7iNIC";
            long timestamp = System.currentTimeMillis() / 1000;
            String nonce = "de208ae41b98a";
            String signature = generateSignature(appId, appKey, timestamp, nonce);

            Map<String, Object> headers = new HashMap<>();
            headers.put("G-Provider", appId);
            headers.put("G-Timestamp", timestamp);
            headers.put("G-Nonce", nonce);
            headers.put("G-Signature", signature);

            Map<String, Object> request = new HashMap<>();
            request.put("request", encryptedRequestString);
            System.out.println("headers");
            System.out.println(headers);
            System.out.println("request");
            System.out.println(request);
            //return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();

            //return ResponseEntity.status(500).body(null);
        }
    }

}


