package com.project.listener;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

import common.message.MessageConstant;

/**
 * 服务启动监听
 * 
 * @author David
 *
 */
public interface ServerStartListener {

	@Input(MessageConstant.SERVER_START)
	SubscribableChannel serverStart();
}