package com.pp.controller.rabbit;

import com.pp.rabbit.RabbitSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rabbit")
public class RabbitControlller {
    @Autowired
    private RabbitSender rabbitSender;

    @GetMapping(value = "/send")
    public String send() {
        rabbitSender.sendHello();
        return "发送成功";
    }
}