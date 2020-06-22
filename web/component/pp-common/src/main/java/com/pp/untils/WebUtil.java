package com.pp.untils;

import com.pp.exception.BizException;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebUtil {

    public static HttpServletRequest getRequest() {
        return getServletRequestAttributes().getRequest();
    }

    public static HttpServletResponse getResponse() {
        return getServletRequestAttributes().getResponse();
    }

    private static ServletRequestAttributes getServletRequestAttributes() {
        return (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    }

    public static String getToken() {
        HttpServletRequest request = getRequest();

        String token = request.getHeader("Authorization");
        if (StringUtils.isEmpty(token)) {
            throw new BizException(HttpStatus.UNAUTHORIZED.value(), "非法请求");
        }
        if (token.startsWith("Bearer ")) {
            token = token.substring("Bearer ".length());
        }
        return token;
    }
}