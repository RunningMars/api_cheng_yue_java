package com.cheng.api.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement //自动管理事务
@Configuration //配置类
public class MybatisPlusConfig {

//    //分页插件
//    @Bean
//    public PaginationInterceptor paginationInterceptor() {
//        return new PaginationInterceptor();
//    }

    /**
     * 新的分页插件,一缓和二缓遵循mybatis的规则,
     * 需要设置 MybatisConfiguration#useDeprecatedExecutor = false 避免缓存出现问题(该属性会在旧插件移除后一同移除)
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        //向Mybatis过滤器链中添加分页拦截器
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        //还可以添加i他的拦截器
        return interceptor;
    }

    //性能分析插件
    /*@Bean
    @Profile({"dev","test"})//设置dev开发、test测试环境开启  保证我们prod的效率
    public PerformanceInterceptor performanceInterceptor(){
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        performanceInterceptor.setMaxTime(2000);//设置sql最大执行时间*ms，如果超过了则不执行
        performanceInterceptor.setFormat(true);//开启sql格式化
        return performanceInterceptor;
    }*/

    //软删除组件
    /*@Bean
    public ISqlInjector sqlInjector(){
        return new LogicSqlInjector();
    }*/



}
