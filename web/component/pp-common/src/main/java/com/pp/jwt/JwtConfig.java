package com.pp.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({JwtProperties.class})
public class JwtConfig {
    @Autowired
    private JwtProperties jwtProperties;

    @Bean
    public JwtUtil getJwtUtil() {
        return new JwtUtil(jwtProperties);
    }
}
