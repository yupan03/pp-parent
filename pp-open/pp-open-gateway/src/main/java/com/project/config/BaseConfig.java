package com.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import common.event.RefreshRoutListener;

@Configuration
public class BaseConfig {
	@Bean
	public RefreshRoutListener routListener() {
		return new RefreshRoutListener();
	}
}