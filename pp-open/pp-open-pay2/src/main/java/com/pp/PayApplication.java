package com.pp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import common.message.ServerStart;
import common.message.ServerStartSendMessage;

@SpringBootApplication
@EnableDiscoveryClient
@EnableBinding(value = { ServerStartSendMessage.class })
public class PayApplication implements CommandLineRunner {
    @Autowired
    private ServerStartSendMessage serverStartSendMessage;

    public static void main(String[] args) {
        SpringApplication.run(PayApplication.class, args);
    }

    /**
     * 当项目启动完成通知网关
     */
    @Override
    public void run(String... args) throws Exception {
        ServerStart serverStart = new ServerStart();
        serverStart.setServiceId("pp-pend-pay2");
        Message<ServerStart> message = MessageBuilder.withPayload(serverStart).build();
        serverStartSendMessage.sendServerStart().send(message);
    }
}