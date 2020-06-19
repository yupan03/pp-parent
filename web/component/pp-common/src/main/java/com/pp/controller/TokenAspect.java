package com.pp.controller;

import com.alibaba.fastjson.JSONObject;
import com.pp.common.result.Result;
import com.pp.jwt.JwtUtil;
import com.pp.jwt.LoginAccount;
import com.pp.jwt.TokenType;
import com.pp.untils.WebUtil;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Aspect
@Component
public class TokenAspect {
    @Resource
    private JwtUtil jwtUtil;

    /**
     * * 定义切面
     */
    @Pointcut("@annotation(com.pp.controller.annontion.ValidToken)")
    public void token() {

    }

    @Before("token()")
    public void validToken() {
        String token = WebUtil.getToken();
        LoginAccount loginAccount = jwtUtil.getAccountFromToken(token);
        HttpServletResponse response = WebUtil.getResponse();
        if (loginAccount == null) {
            // 非法Token
            this.write(response, new Result(401, "非法Token"));
        } else if (loginAccount.getTokenType() == TokenType.OVERDUE) {
            // Token已过期
            this.write(response, new Result(401, "Token已过期"));
        } else if (loginAccount.getTokenType() == TokenType.WILL_EXPIRE) {
            // Token将过期
            response.setHeader("Authorization", jwtUtil.generateToken(loginAccount));
        }
    }

    private void write(HttpServletResponse response, Result result) {
        try {
            PrintWriter writer = response.getWriter();
            writer.println(JSONObject.toJSONString(result));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}