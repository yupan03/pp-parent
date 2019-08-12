package com.pp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@MapperScan("com.pp.dao")
@EnableDiscoveryClient
public class PayApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(PayApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}