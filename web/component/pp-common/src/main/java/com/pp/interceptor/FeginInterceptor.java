package com.pp.interceptor;

import com.pp.untils.WebUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;

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
        HttpServletRequest request = WebUtil.getRequest();
        // 异步处理
        if (request == null)
            return;
        Map<String, String> headers = getHeaders(request);
        for (String headerName : headers.keySet()) {
            requestTemplate.header(headerName, getHeaders(request).get(headerName));
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