package com.project;

import feign.Retryer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@MapperScan("com.project.dao")
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableDiscoveryClient
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }

    /**
     * Feign重试
     * 
     * @return
     */
    @Bean
    public Retryer feignRetryer() {
        return new Retryer.Default();
    }
}