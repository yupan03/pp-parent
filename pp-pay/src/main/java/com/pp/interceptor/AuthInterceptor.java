package com.pp.interceptor;

import com.pp.dao.OutsideAppDao;
import com.pp.entity.table.OutsideApp;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 认证拦截器
 * 对权限和资源的统一处理
 *
 * @Author David
 */
public class AuthInterceptor implements HandlerInterceptor {
    private OutsideAppDao outsideAppDao;

    public AuthInterceptor(OutsideAppDao outsideAppDao) {
        this.outsideAppDao = outsideAppDao;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        System.out.println(uri);

        // 需要排除swagger2的接口，不需要认证权限
        if (uri.equals("/doc.html") || uri.equals("/v2/api-json")) {
            return true;
        }

        // 判断这个是否存在
        String app = request.getHeader("app");
        String secret = request.getHeader("secret");

        OutsideApp outsideApp = outsideAppDao.selectByAppAndSecret(app, secret);

        if (outsideApp == null) {
            // 提示没有权限
            return false;
        }

        return true;
    }
}