package com.pp;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewaySecurityApplication {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(GatewaySecurityApplication.class);

        springApplication.setBannerMode(Banner.Mode.OFF);
        springApplication.run(args);
    }
}