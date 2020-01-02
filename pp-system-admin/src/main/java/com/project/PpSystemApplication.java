package com.project;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@MapperScan("com.project.dao")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class PpSystemApplication {

    public static void main(String[] args) {

        SpringApplication springApplication = new SpringApplication(PpSystemApplication.class);

        springApplication.setBannerMode(Banner.Mode.OFF);
        springApplication.run(args);
    }
}