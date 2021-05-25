package com.pp.controller.user;

import com.pp.controller.BaseController;
import com.pp.controller.annontion.ValidToken;
import com.pp.entity.table.User;
import com.pp.jwt.JwtUtil;
import com.pp.jwt.LoginAccount;
import com.pp.result.Result;
import com.pp.service.user.UserService;
import com.pp.utils.SysUtils;
import lombok.Data;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户管理
 */
@RestController
@RequestMapping(value = "/user")
public class UserController extends BaseController {
    @Resource
    private JwtUtil jwtUtil;
    @Resource
    private UserService userService;

    /**
     * @param user 用户信息
     */
    @PostMapping(value = "/login")
    public Result<Void> getToken(@RequestBody LoginUser user, HttpServletResponse response) {
        LoginAccount account = new LoginAccount();
        account.setUsername("yupan");
        account.setLoginTime(SysUtils.getCurrentTime());
        account.setType((byte) 0);
        response.setHeader("Authorization", jwtUtil.generateToken(account));
        return super.result();
    }

    @RequestMapping(value = "/say")
    public Result<String> sayHello() throws InterruptedException {
        Thread.sleep(500);
        return super.result("hello world");
    }

    @Data
    static class LoginUser {
        /**
         * 用户名称
         */
        private String username;
        /**
         * 密码
         */
        private String password;
    }

    @Data
    static class UserAdd {
        private String account;
        private String name;
    }

    @PostMapping(value = "/add")
    @ValidToken
    public Result add(@RequestBody User userAdd) {
        userService.addUser(userAdd);
        return super.result();
    }

    @RequestMapping(value = "/list")
    @ValidToken
    public Result<List<User>> userList() {
        List<User> users = new ArrayList<>();
        User user = new User();
        user.setId(1L);
        user.setAccount("yupan");
        user.setName("吁盼");
        users.add(user);
        return super.result(users);
    }
}