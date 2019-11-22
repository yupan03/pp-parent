package com.project.controller.user;

import com.project.controller.CommonController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

/**
 * 前端控制器
 *
 * @author David
 * @since 2019-08-02
 */
@RestController
@RequestMapping(value = "/user")
public class UserController extends CommonController {
    @RequestMapping(value = "/say")
    public String sayHello(HttpServletResponse response) {
        response.setHeader("token", "token");
        return "Hello world!";
    }
}