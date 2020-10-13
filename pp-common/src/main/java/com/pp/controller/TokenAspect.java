package com.pp.controller;

import com.pp.exception.BizException;
import com.pp.jwt.JwtUtil;
import com.pp.jwt.LoginAccount;
import com.pp.jwt.TokenType;
import com.pp.utils.WebUtil;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@Aspect
@Component
@Order(1)
public class TokenAspect {
    @Resource
    private JwtUtil jwtUtil;

    /**
     * 定义切面
     */
    @Pointcut("@annotation(com.pp.controller.annontion.ValidToken)")
    public void token() {
    }

    @Before("token()")
    public void validToken() {
        String token = WebUtil.getToken();
        LoginAccount loginAccount = jwtUtil.getAccountFromToken(token);
        HttpServletResponse response = WebUtil.getResponse();
        // 响应头增加token
        response.setHeader("Authorization", token);
        if (loginAccount == null) {
            // 非法Token
            throw new BizException(401, "非法Token");
        } else if (loginAccount.getTokenType() == TokenType.OVERDUE) {
            // Token已过期
            throw new BizException(401, "Token已过期");
        } else if (loginAccount.getTokenType() == TokenType.WILL_EXPIRE) {
            // Token将过期,生成新的token返回给前段
            response.setHeader("Authorization", jwtUtil.generateToken(loginAccount));
        }
    }
}