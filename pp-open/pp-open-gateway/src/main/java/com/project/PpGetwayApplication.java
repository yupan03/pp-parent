package com.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import feign.Retryer;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class PpGetwayApplication {

	public static void main(String[] args) {
		SpringApplication.run(PpGetwayApplication.class, args);
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