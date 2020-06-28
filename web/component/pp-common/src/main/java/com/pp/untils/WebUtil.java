package com.pp.untils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebUtil {
    /**
     * 获取HttpServletRequest
     *
     * @return HttpServletRequest对象
     */
    public static HttpServletRequest getRequest() {
        return getServletRequestAttributes().getRequest();
    }

    /**
     * 获取HttpServletResponse
     *
     * @return HttpServletResponse对象
     */
    public static HttpServletResponse getResponse() {
        return getServletRequestAttributes().getResponse();
    }

    /**
     * 获取ServletRequestAttributes
     *
     * @return ServletRequestAttributes对象
     */
    private static ServletRequestAttributes getServletRequestAttributes() {
        return (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    }

    /**
     * 获取请求头token
     *
     * @return token字符串
     */
    public static String getToken() {
        return getRequest().getHeader("Authorization");
    }
}