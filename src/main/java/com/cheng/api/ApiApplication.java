package com.cheng.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.cheng.api.mapper")
@SpringBootApplication
public class ApiApplication {

    public static void main(String[] args) {
        //System.setProperty("file.encoding", "UTF-8");
        SpringApplication.run(ApiApplication.class, args);
    }

}
