package com.pp.controller;

import com.pp.common.VerificationCode;
import com.pp.common.result.Result;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/")
public class LoginController {

    @PostMapping(value = "/login")
    public Result doLogin(String username, String password) {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
//            throw new BusinessException(BusinessStatus.LOGIN_FAIL, "账号或密码不能为空！");
        }

        return new Result(HttpStatus.OK.value(), "登录成功");
    }

    @RequestMapping(value = "/verificationCode.do", method = {RequestMethod.GET})
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