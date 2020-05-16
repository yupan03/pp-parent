package com.pp.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class RabbitMQConfig {
    @Resource
    private RabbitTemplate rabbitTemplate;

    // 自定义JSON消息序列化器
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Queue testQueue() {
        return new Queue("testQueue", true);
    }

    @Bean
    public DirectExchange testDirectExchange() {
        return new DirectExchange("testDirectExchange", true, false);
    }

    @Bean
    public Binding bindingDirect() {
        return BindingBuilder.bind(testQueue()).to(testDirectExchange()).with("testRoute");
    }
}