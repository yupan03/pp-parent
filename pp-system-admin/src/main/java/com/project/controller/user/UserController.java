package com.project.controller.user;

import com.project.controller.BaseController;
import com.project.controller.user.param.LoginUser;
import com.project.controller.user.param.UserAdd;
import com.project.service.user.UserService;
import common.result.Result;
import common.result.ResultObj;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jwt.JwtUtil;
import jwt.LoginAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@Api(tags = "用户管理")
public class UserController extends BaseController {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserService userService;

    @PostMapping(value = "login")
    public String getToken(@RequestBody LoginUser user) {
        LoginAccount account = new LoginAccount();
        account.setUsername(user.getUsername());
        return jwtUtil.generateToken(account);
    }

    @RequestMapping(value = "/say")
    public ResultObj<String> sayHello() {
        return super.resultObj(HttpStatus.OK.value(), "成功", "hello world");
    }

    @PostMapping(value = "/add")
    @ApiOperation(value = "/add", tags = "新增用户")
    public Result add(@RequestBody UserAdd userAdd) {
        userService.addUser(userAdd);
        return super.result(HttpStatus.OK.value(), "新增客户成功");
    }
}