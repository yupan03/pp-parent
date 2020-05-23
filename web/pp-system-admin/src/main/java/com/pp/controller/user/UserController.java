package com.pp.controller.user;

import com.pp.common.result.Result;
import com.pp.common.result.ResultObj;
import com.pp.controller.BaseController;
import com.pp.controller.user.param.LoginUser;
import com.pp.entity.table.User;
import com.pp.service.user.UserService;
import jwt.JwtUtil;
import jwt.LoginAccount;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/user")
public class UserController extends BaseController {
    @Resource
    private JwtUtil jwtUtil;
    @Resource
    private UserService userService;

    @PostMapping(value = "login")
    public String getToken(@RequestBody LoginUser user) {
        LoginAccount account = new LoginAccount();
        account.setUsername(user.getUsername());
        return jwtUtil.generateToken(account);
    }

    @RequestMapping(value = "/say")
    public ResultObj<String> sayHello() {
        return super.resultObj("hello world");
    }

    @PostMapping(value = "/add")
    public Result add(@RequestBody User userAdd) {
        userService.addUser(userAdd);
        return super.result();
    }
}