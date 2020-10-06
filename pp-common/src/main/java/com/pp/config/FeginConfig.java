package com.pp.config;

import com.pp.interceptor.FeginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeginConfig {

    @Bean
    public FeginInterceptor feginInterceptor() {
        return new FeginInterceptor();
    }
}