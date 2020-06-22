package com.pp.config;

import com.pp.exception.BizException;
import com.pp.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.ServletException;

/**
 * 统一异常处理
 *
 * @author David
 */
@RestControllerAdvice
public class ExceptionControllerAdvice {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 自定义系统业务异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = BizException.class)
    @ResponseBody
    public Result handlerProjectException(BizException e) {
        logger.info(e.getMsg());
        return new Result(e.getStatus(), e.getMsg());
    }

    /**
     * 请求异常处理 配置文件增加 spring:mvc:throw-exception-if-no-handler-found: true
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = ServletException.class)
    @ResponseBody
    public Result handlerServletException(ServletException e) {
        if (e instanceof HttpRequestMethodNotSupportedException) {
            // 405请求方式错误
            return new Result(HttpStatus.METHOD_NOT_ALLOWED.value(), e.getMessage());
        } else if (e instanceof NoHandlerFoundException) {
            // 404 请求未找到
            return new Result(HttpStatus.NOT_FOUND.value(), e.getMessage());
        } else {
            return new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        }
    }
}