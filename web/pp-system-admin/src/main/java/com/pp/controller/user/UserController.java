package com.pp.controller.user;

import com.pp.common.result.Result;
import com.pp.common.result.ResultObj;
import com.pp.common.result.ResultPage;
import com.pp.common.utils.SysUtils;
import com.pp.controller.BaseController;
import com.pp.controller.annontion.ValidToken;
import com.pp.controller.user.param.LoginUser;
import com.pp.entity.table.User;
import com.pp.jwt.JwtUtil;
import com.pp.jwt.LoginAccount;
import com.pp.service.user.UserService;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController extends BaseController {
    @Resource
    private JwtUtil jwtUtil;
    @Resource
    private UserService userService;

    @PostMapping(value = "/login")
    public Result getToken(@RequestBody LoginUser user, HttpServletResponse response) {
        LoginAccount account = new LoginAccount();
        account.setUsername("yupan");
        account.setLoginTime(SysUtils.getCurrentTime());
        account.setType((byte) 0);
        response.setHeader("Authorization", jwtUtil.generateToken(account));
        response.setHeader("Authorization", jwtUtil.generateToken(account));
        return super.result();
    }

    @RequestMapping(value = "/say")
    @ValidToken
    public ResultObj<String> sayHello() {
        return super.resultObj("hello world");
    }

    @PostMapping(value = "/add")
    @ValidToken
    public Result add(@RequestBody User userAdd) {
        userService.addUser(userAdd);
        return super.result();
    }

    @PostMapping(value = "/list")
    @ValidToken
    public ResultPage<User> userList() {
        List<User> users = new ArrayList<>();
        User user = new User();
        user.setId(1L);
        user.setAccount("yupan");
        user.setName("吁盼");
        users.add(user);
        return new ResultPage<>(200, "success", users, 20, 1, 100L);
    }
}