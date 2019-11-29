package com.pp.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 认证拦截器
 * @Author David
 */
public class AuthInterceptor implements HandlerInterceptor {
    private Object object;


    public AuthInterceptor(Object object){
        this.object = object;
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        request.getHeader("appId");
        request.getHeader("secert");

        String uri = request.getRequestURI();
        System.out.println(uri);
        // 认证校验

        // 权限校验
        return true;
    }
}
