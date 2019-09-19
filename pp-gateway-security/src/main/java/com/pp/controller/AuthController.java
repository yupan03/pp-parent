package com.pp.controller;

import java.util.Date;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import common.result.ResultObj;
import common.result.status.ResultStatusEnum;
import jwt.JwtUtil;
import jwt.LoginAccount;

@RestController
public class AuthController {

    @RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
    public ResultObj<String> login(String username, String password) {

        LoginAccount account = new LoginAccount();

        account.setUsername(username);
        account.setLoginTime(new Date());
        account.setType("1");

        ResultObj<String> result = new ResultObj<>(ResultStatusEnum.SUCCESS);

        result.setData(new JwtUtil().generateToken(account));

        return result;
    }
}