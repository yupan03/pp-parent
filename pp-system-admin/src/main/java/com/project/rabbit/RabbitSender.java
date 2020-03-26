package com.project.rabbit;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitSender implements RabbitTemplate.ReturnCallback {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendHello() {
        rabbitTemplate.convertAndSend("testQueue", "hello wolrld");
        rabbitTemplate.setReturnCallback(this);
        rabbitTemplate.setConfirmCallback(((correlationData, ack, cause) -> {
            if (!ack) {
                System.out.println("消息发送失败" + cause);
            } else {
                System.out.println("消息发送成功");
            }
        }));
    }

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        System.out.println(message.getMessageProperties().getCorrelationId());
		System.out.println("消息发送至RabbitMq确认");
    }
}