package com.project.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itmuch.lightsecurity.jwt.JwtOperator;
import com.itmuch.lightsecurity.jwt.ReactiveUserOperator;
import com.itmuch.lightsecurity.jwt.User;

@RestController
public class LoginController {
    @Autowired
    private ReactiveUserOperator userOperator;
    @Autowired
    private JwtOperator operator;

    @RequestMapping(value = "login")
    public String login() {
        User user = User.builder().id("1").username("yupan").roles(Arrays.asList("user", "admin")).build();
        return operator.generateToken(user);
    }
}