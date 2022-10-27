package com.yxw.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.apache.ibatis.reflection.MetaObject;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

/**
 * @author : yuxinwen
 * @mingcheng : CRM
 * @模块 : com.yxw.config
 * @date :2022/9/11 15:36
 */
@Configuration
@MapperScan("com.yxw.mapper")
public class MybatisPlusConfig implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        //属性名称，不是字段名称
        setFieldValByName("createTime", new Date(), metaObject);
        setFieldValByName("updateTime", new Date(), metaObject);

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        setFieldValByName("updateTime", new Date(), metaObject);
    }

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
