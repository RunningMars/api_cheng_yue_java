package com.cheng.api.controller;


import com.aliyun.oss.OSS;
import com.cheng.api.common.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;

@RestController
public class FileUploadController {

    @Autowired
    private OSS ossClient;

    @Value("${spring.aliyun.oss.bucketName}")
    private String bucketName;

    @PostMapping("/uploadImage")
    public R<HashMap<String, Object>> upload(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        // 生成OSS的文件路径
        String key = "image/" + new Date().getTime() + fileName.substring(fileName.lastIndexOf("."));
        try (InputStream inputStream = file.getInputStream()) {
            // 上传文件
            ossClient.putObject(bucketName, key, inputStream);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error("上传失败");
        }
        String url = "https://cheng-yue.oss-cn-chengdu.aliyuncs.com/" + key;
        HashMap<String, Object> objectHashMap = new HashMap<>();
        objectHashMap.put("url",url);
        objectHashMap.put("folder","image");
        return R.success(objectHashMap);
    }
}