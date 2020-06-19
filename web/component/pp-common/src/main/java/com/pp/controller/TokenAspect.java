package com.pp.controller;

import com.pp.jwt.JwtUtil;
import com.pp.jwt.LoginAccount;
import com.pp.untils.WebUtil;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@Aspect
@Component
public class TokenAspect {
    @Resource
    private JwtUtil jwtUtil;
    @Resource(name = "database0")
    private StringRedisTemplate stringRedisTemplate;

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
    }
}