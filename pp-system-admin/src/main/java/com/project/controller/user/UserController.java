package com.project.controller.user;

import com.project.controller.CommonController;
import com.project.controller.user.param.LoginUser;
import com.project.dao.UserDao;
import com.project.token.TokenCheck;
import jwt.JwtUtil;
import jwt.LoginAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 前端控制器
 *
 * @author David
 * @since 2019-08-02
 */
@RestController
@RequestMapping(value = "/user")
public class UserController extends CommonController {
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping(value = "login")
    public String getToken(@RequestBody LoginUser user) {
        LoginAccount account = new LoginAccount();
        account.setUsername(user.getUsername());
        return jwtUtil.generateToken(account);
    }


    @RequestMapping(value = "/say")
    @TokenCheck
    public String sayHello() {
        return "Hello world!";
    }
}