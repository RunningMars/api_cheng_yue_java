package com.cheng.api.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AliYunOssConfig {

    @Value("${spring.aliyun.oss.endpoint}")
    private String endpoint;

    @Value("${spring.aliyun.oss.accessKeyId}")
    private String accessKeyId;

    @Value("${spring.aliyun.oss.accessKeySecret}")
    private String accessKeySecret;

    @Bean
    public OSS ossClient() {
        return new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    }
}