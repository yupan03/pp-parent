package com.pp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    @RequestMapping(value = "/login")
    public String login(String username, String password) {
        return null;
    }
}