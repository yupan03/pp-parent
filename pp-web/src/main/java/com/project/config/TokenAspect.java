package com.project.config;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import common.annotation.PassToken;
import common.jwt.JWTUtils;
import common.result.exception.ResultException;
import common.result.status.ResultStatusEnum;

/**
 * Token前置增强
 * 
 * @author David
 *
 */
@Aspect
@Component
public class TokenAspect {

    /**
     ** 定义切面
     */
    @Pointcut("execution(public * com.project.controller..*.*(..))")
    public void tokenCut() {
    }

    @Before("tokenCut()")
    public void around(JoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();

        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.value()) {
                return;
            }
        }

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();// 获取request

        String token = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (StringUtils.isEmpty(token)) {
            throw new ResultException(ResultStatusEnum.AUTH_FAIL, "请登录");
        }

        // 检验token的失效性
        if (!JWTUtils.isTokenExpired(token)) {
            throw new ResultException(ResultStatusEnum.AUTH_FAIL, "token失效，请重新登录");
        }
    }
}