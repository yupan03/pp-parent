package com.pp.controller.user;

import com.pp.common.result.Result;
import com.pp.common.result.ResultObj;
import com.pp.common.result.ResultPage;
import com.pp.controller.BaseController;
import com.pp.controller.user.param.LoginUser;
import com.pp.entity.table.User;
import com.pp.service.user.UserService;
import jwt.JwtUtil;
import jwt.LoginAccount;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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

    @GetMapping(value = "/list")
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