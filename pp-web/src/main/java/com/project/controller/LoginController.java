package com.project.controller;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.exception.BusinessException;
import com.project.exception.BusinessStatus;

import common.VerificationCode;
import common.result.ResultObj;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "登录")
@RestController
@RequestMapping(value = "/")
public class LoginController {

    @ApiOperation(value = "登录")
    @PostMapping(value = "/login")
    public ResultObj<String> doLogin(String username, String password) {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            throw new BusinessException(BusinessStatus.LOGIN_FAIL, "账号或密码不能为空！");
        }

        return new ResultObj<String>(HttpStatus.OK.value());
    }

    @ApiOperation(value = "验证码")
    @RequestMapping(value = "/verificationCode.do", method = { RequestMethod.GET })
    public void verificationCode(HttpServletRequest request, HttpServletResponse response) {
        VerificationCode code = new VerificationCode(4);

        String codeStr = code.getCode();

        request.getSession().setAttribute("verificationCode", codeStr);

        response.setCharacterEncoding("UTF-8");

        try {
            ImageIO.write(code.getImage(codeStr), "JPEG", response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}