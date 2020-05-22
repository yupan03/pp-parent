package com.pp.controller.user;

import com.pp.common.result.Result;
import com.pp.common.result.ResultObj;
import com.pp.controller.BaseController;
import com.pp.controller.user.param.LoginUser;
import com.pp.entity.table.User;
import com.pp.service.user.UserService;
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
public class UserController extends BaseController {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserService userService;

    /**
     * @api {POST} /user/login getToken
     * @apiVersion 1.0.0
     * @apiGroup UserController
     * @apiName getToken
     * @apiDescription 登录接口
     * @apiParam (请求体) {String} username
     * @apiParam (请求体) {String} password
     * @apiParamExample 请求体示例
     * {"password":"wKgc","username":"qd5ZmLR"}
     * @apiSuccess (响应结果) {String} response
     * @apiSuccessExample 响应结果示例
     * "ii28yrn"
     */
    @PostMapping(value = "login")
    public String getToken(@RequestBody LoginUser user) {
        LoginAccount account = new LoginAccount();
        account.setUsername(user.getUsername());
        return jwtUtil.generateToken(account);
    }

    /**
     * @api {GET} /user/say sayHello
     * @apiVersion 1.0.0
     * @apiGroup UserController
     * @apiName sayHello
     * @apiSuccess (响应结果) {String} data
     * @apiSuccess (响应结果) {Number} status
     * @apiSuccess (响应结果) {String} msg
     * @apiSuccessExample 响应结果示例
     * {"msg":"bb","data":"2RA","status":7117}
     */
    @RequestMapping(value = "/say")
    public ResultObj<String> sayHello() {
        return super.resultObj("hello world", String.class);
    }

    @PostMapping(value = "/add")
    public Result add(@RequestBody User userAdd) {
        userService.addUser(userAdd);
        return super.result(HttpStatus.OK.value(), "新增客户成功");
    }
}