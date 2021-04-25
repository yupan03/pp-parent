package com.pp.jwt;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * JWT配置
 */
@Configuration
@EnableConfigurationProperties({JwtProperties.class})
public class JwtConfig {
    @Resource
    private JwtProperties jwtProperties;

    @Bean
    public JwtUtil getJwtUtil() {
        return new JwtUtil(jwtProperties);
    }
}
