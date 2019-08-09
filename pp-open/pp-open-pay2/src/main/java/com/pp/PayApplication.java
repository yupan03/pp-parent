package com.pp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.bus.jackson.RemoteApplicationEventScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import common.event.RefreshRoutEvent;

@SpringBootApplication
@EnableDiscoveryClient
@RemoteApplicationEventScan(basePackageClasses = RefreshRoutEvent.class)
public class PayApplication {
	public static void main(String[] args) {
		SpringApplication.run(PayApplication.class, args);
	}
}