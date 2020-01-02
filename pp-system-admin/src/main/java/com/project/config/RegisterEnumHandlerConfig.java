package com.project.config;

import com.project.constant.GeneralTypeHandler;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.stereotype.Component;

@Component
public class RegisterEnumHandlerConfig implements ConfigurationCustomizer {

    @Override
    public void customize(Configuration configuration) {
        TypeHandlerRegistry registry = configuration.getTypeHandlerRegistry();
        registry.setDefaultEnumTypeHandler(GeneralTypeHandler.class);
    }
}
