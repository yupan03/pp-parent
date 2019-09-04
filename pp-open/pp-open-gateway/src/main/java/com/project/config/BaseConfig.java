//package com.project.config;
//
//import org.springframework.cloud.stream.annotation.EnableBinding;
//import org.springframework.cloud.stream.annotation.StreamListener;
//import org.springframework.context.annotation.Configuration;
//
//import com.project.listener.ServerStartListener;
//
//import rabbit.config.MessageConstant;
//import rabbit.config.ServerStart;
//
//@Configuration
//@EnableBinding(value = { ServerStartListener.class })
//public class BaseConfig {
////    @StreamListener(MessageConstant.SERVER_START)
////    public void serverStart(ServerStart serverStart) {
////        System.out.println("收到消息:" + serverStart.getServiceId());
////    }
//}