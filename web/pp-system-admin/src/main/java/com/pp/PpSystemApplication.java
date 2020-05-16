package com.pp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.io.File;

@SpringBootApplication
@MapperScan("com.project.dao")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class PpSystemApplication implements CommandLineRunner {
    @Value("${path}")
    private String path;

    @Override
    public void run(String... args) throws Exception {
        // 初始化文件夹
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        // 对项目做一些初始化动作，比如redis缓存

    }

    public static void main(String[] args) {

        SpringApplication springApplication = new SpringApplication(PpSystemApplication.class);

        springApplication.setBannerMode(Banner.Mode.OFF);
        springApplication.run(args);
    }
}