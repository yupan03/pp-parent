package com.pp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import common.event.RefreshRoutEvent;

@RestController
@RefreshScope
public class TestController {
	private ApplicationContext context;

	@Autowired
	public TestController(ApplicationContext context) {
		this.context = context;
	}

	@GetMapping(value = "say")
	public String sendMessage() {
		context.publishEvent(new RefreshRoutEvent(this, context.getId(), "*:**"));
		return "hello world";
	}
}