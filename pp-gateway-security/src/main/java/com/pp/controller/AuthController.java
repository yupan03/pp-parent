package com.pp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import common.result.ResultObj;
import common.result.status.ResultStatusEnum;
import common.utils.SysUtils;
import jwt.JwtUtil;
import jwt.LoginAccount;

@RestController
public class AuthController {
    @RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
    public ResultObj<String> login(@RequestParam("username") String username,
            @RequestParam("password") String password) {
        System.out.println(username + "---------" + password);

        LoginAccount account = new LoginAccount();

        account.setUsername(username);
        account.setLoginTime(SysUtils.getCurrentTime());
        account.setType("1");

        ResultObj<String> result = new ResultObj<>(ResultStatusEnum.SUCCESS);

        result.setData(new JwtUtil().generateToken(account));

        return result;
    }
}