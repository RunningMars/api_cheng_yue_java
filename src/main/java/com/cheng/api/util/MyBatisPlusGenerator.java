package com.cheng.api.util;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.cheng.api.controller.BaseController;
import com.cheng.api.entity.BaseEntity;

import java.util.ArrayList;
import java.util.Collections;

public class MyBatisPlusGenerator {


    //@Repository //将MemberMapper类交给 springboot 容器管理

    public static void main(String[] args) {
        ArrayList<String> tables = new ArrayList<>();
        //tables.add("member");
        //tables.add("member_request");
//        tables.add("member_favorite");
//        tables.add("member_thumbs_up");
        //tables.add("send_sms");
        //tables.add("mobile_validate_code");
        //tables.add("user");
//        tables.add("chat_room");
        //tables.add("chat_room_member");
        //tables.add("chat_room_message");
        //tables.add("member_image");


        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig.Builder("jdbc:mysql://localhost:3306/cheng_yue", "root", "rdgrdg").build();

        // 全局配置
        GlobalConfig gc = new GlobalConfig.Builder()
                .outputDir(System.getProperty("user.dir") + "/src/main/java") //输出路径(写到 java 目录)
                .enableSwagger()  //开启 swagger
                .commentDate("yyyy-MM-dd")
                .dateType(DateType.ONLY_DATE)   //定义生成的实体类中日期的类型 TIME_PACK=LocalDateTime;ONLY_DATE=Date;
                .author("rdg") //作者
                //.fileOverride() //开启覆盖之前的文件
                .disableOpenDir()   //禁止打开输出目录，默认打开
                .build();

        // 包配置
        PackageConfig pc = new PackageConfig.Builder()
                .parent("com.cheng.api")
                //留空请求路径中就会少一个层级 原层级/member//list  现层级 /list
                //.moduleName("")
                .entity("entity")
                .mapper("mapper")
                .service("service")
                .serviceImpl("service.impl")
                .controller("controller")
                .xml("mapper")
                .pathInfo(Collections.singletonMap(OutputFile.mapperXml,System.getProperty("user.dir") +  "/src/main/resources/mapper")) //配置 mapper.xml 路径信息：项目的 resources 目录下
                .build();

        // 策略配置
        StrategyConfig strategyConfig = new StrategyConfig.Builder()
                .enableCapitalMode()    //开启大写命名
                .enableSkipView()   //创建实体类的时候跳过视图
                .addInclude(tables)
                //.addTablePrefix("t_")//设置表前缀过滤
                //.addTableSuffix(listTableSuffix) //设置 过滤 表的后缀

                //service 策略配置
                .serviceBuilder()
                .formatServiceFileName("%sService")//格式化 service 接口文件名称
                .formatServiceImplFileName("%sServiceImpl")//格式化 service 接口文件名称

                //实体类策略配置
                .entityBuilder()
                .enableChainModel() //开启链式模型
                //.disableSerialVersionUID()  //默认是开启实体类序列化，可以手动disable使它不序列化。由于项目中需要使用序列化就按照默认开启了
                .enableTableFieldAnnotation()       // 开启生成实体时生成字段注解
                .enableLombok() //开启 Lombok
                //.versionColumnName("version")   //乐观锁字段名(数据库)
                //.versionPropertyName("version") //乐观锁属性名(实体)
                //.logicDeleteColumnName("deleted")   //逻辑删除字段名(数据库)
                //.logicDeletePropertyName("deleteFlag")  //逻辑删除属性名(实体)
                .naming(NamingStrategy.underline_to_camel)  //数据库表映射到实体的命名策略：默认是下划线转驼峰命。这里可以不设置
                .columnNaming(NamingStrategy.underline_to_camel)    //数据库表字段映射到实体的命名策略：下划线转驼峰命。(默认是和naming一致，所以也可以不设置)
                .addTableFills(
                        new Column("created_at", FieldFill.INSERT),
                        new Column("updated_at", FieldFill.UPDATE)
                )   //添加表字段填充，"create_time"字段自动填充为插入时间，"modify_time"字段自动填充为插入修改时间
                .idType(IdType.AUTO)    //设置主键自增
                .superClass(BaseEntity.class)

                //Controller策略配置
                .controllerBuilder()
                .superClass(BaseController.class)
                .formatFileName("%sController")//格式化实体名称，%s取消首字母I,
                .enableRestStyle()

                //Mapper策略配置
                .mapperBuilder()
                .enableBaseResultMap() //生成通用的 resultMap
                //.enableBaseColumnList() //启用 BaseColumnList
                .superClass(BaseMapper.class)
                .formatMapperFileName("%sMapper")//格式化Dao类名称
                .enableMapperAnnotation()//开启 @Mapper 注解
                .formatMapperFileName("%sMapper")   //格式化Mapper文件名称
                .formatXmlFileName("%sMapper")//格式化xml文件名称
                .build();

        // 执行生成
        AutoGenerator ag = new AutoGenerator(dsc);
        ag.global(gc);
        ag.packageInfo(pc);
        ag.strategy(strategyConfig);
        ag.execute();
    }
}