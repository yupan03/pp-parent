package com.pp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.pp.dao")
public class GatewaySecurityApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewaySecurityApplication.class, args);
    }
}