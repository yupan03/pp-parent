package com.pp.untils;

import com.pp.common.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class WebUtil {
    public static String getUserId() {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();

        String userId = request.getHeader("userId");
        if (StringUtils.isEmpty(userId)) {
            throw new BusinessException(HttpStatus.UNAUTHORIZED.value(), "非法请求");
        }
        return userId;
    }
}