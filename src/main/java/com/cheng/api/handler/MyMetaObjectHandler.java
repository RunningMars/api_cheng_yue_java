package com.cheng.api.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    //插入时的自动填充策略
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert auto fill ...");
        this.setFieldValByName("createdAt",new Date(),metaObject);
        this.setFieldValByName("updatedAt",new Date(),metaObject);
    }

    //更新时的自动填充策略
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update auto fill ... ");
        this.setFieldValByName("updatedAt",new Date(),metaObject);
    }
}
