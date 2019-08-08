package com.project;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PpGetwayApplication implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(PpGetwayApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// 启动时执行任务
		// 刷新路由
//		dynamicRouteServiceImpl.refresh();
	}
}