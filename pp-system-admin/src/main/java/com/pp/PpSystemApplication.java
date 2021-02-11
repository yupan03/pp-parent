package com.pp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@MapperScan("com.pp.dao")
@EnableDiscoveryClient
@EnableConfigurationProperties
public class PpSystemApplication {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(PpSystemApplication.class);

        springApplication.setBannerMode(Banner.Mode.OFF);
        springApplication.run(args);
    }
}