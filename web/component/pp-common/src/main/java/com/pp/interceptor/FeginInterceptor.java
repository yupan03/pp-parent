package com.pp.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 解决服务之间请求头传递
 */
public class FeginInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        HttpServletRequest httpServletRequest = getHttpServletRequest();
        // 异步处理
        if (httpServletRequest == null)
            return;
        Map<String, String> headers = getHeaders(httpServletRequest);
        for (String headerName : headers.keySet()) {
            requestTemplate.header(headerName, getHeaders(getHttpServletRequest()).get(headerName));
        }
    }

    private HttpServletRequest getHttpServletRequest() {
        try {
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
            if (requestAttributes == null) {
                return null;
            }
            return ((ServletRequestAttributes) requestAttributes).getRequest();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Map<String, String> getHeaders(HttpServletRequest request) {
        Map<String, String> map = new LinkedHashMap<>();
        Enumeration<String> enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()) {
            String key = enumeration.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        return map;
    }
}