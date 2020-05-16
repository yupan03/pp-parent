package com.pp.config;

import com.pp.constant.GeneralTypeHandler;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.stereotype.Component;

@Component
public class RegisterEnumHandlerConfig implements ConfigurationCustomizer {

    @Override
    public void customize(Configuration configuration) {
        TypeHandlerRegistry registry = configuration.getTypeHandlerRegistry();
        // 设置默认枚举处理类
        registry.setDefaultEnumTypeHandler(GeneralTypeHandler.class);
    }
}