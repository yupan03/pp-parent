package com.project.config;

import org.apache.ibatis.type.TypeHandlerRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.project.constant.GeneralTypeHandler;

@Configuration
public class Config {

    /**
     * 通过@Configuration使用MyBatis配置类 查看链接http://www.yyjjssnn.cn/articles/839.html
     * http://www.mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/
     * 
     * @return
     */
//    @Bean
//    public ConfigurationCustomizer configurationCustomizer() {
//        return new ConfigurationCustomizer() {
//            @Override
//            public void customize(MybatisConfiguration configuration) {
//                TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
//                // 更改mybatis默认枚举处理
//                typeHandlerRegistry.setDefaultEnumTypeHandler(GeneralTypeHandler.class);
//            }
//        };
//    }
}