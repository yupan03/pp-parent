package com.project.config;

import java.sql.SQLException;

import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import common.result.ResultException;
import common.result.ResultObj;
import common.result.ResultStatusEnum;

/**
 * 统一异常处理
 * 
 * @author david
 */
@RestControllerAdvice
public class ExceptionControllerAdvice {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     ** 自定义系统业务异常处理
     * 
     * @param e
     * @return
     */
    @ExceptionHandler(value = ResultException.class)
    @ResponseBody
    public ResultObj<?> handlerProjectException(ResultException e) {
        logger.info(e.getMsg());
        return new ResultObj<>(e.getStatusEnum(), e.getMsg());
    }

    /**
     ** 请求异常处理 配置文件增加 spring:mvc:throw-exception-if-no-handler-found: true
     * 
     * @param e
     * @return
     */
    @ExceptionHandler(value = ServletException.class)
    @ResponseBody
    public ResultObj<?> handlerServletException(ServletException e) {
        if (e instanceof HttpRequestMethodNotSupportedException) {
            // 405请求方式错误
            return new ResultObj<>(ResultStatusEnum.URL_METHOD_EOOR, e.getMessage());
        } else if (e instanceof NoHandlerFoundException) {
            // 404 请求未找到
            return new ResultObj<>(ResultStatusEnum.URL_NOT_FOUND, e.getMessage());
        } else {
            return new ResultObj<>(ResultStatusEnum.ERROR, e.getMessage());
        }
    }

    /**
     ** 数据库异常处理
     * 
     * @param e
     * @return
     */
    @ExceptionHandler(value = SQLException.class)
    @ResponseBody
    public ResultObj<?> handlerSQLException(SQLException e) {
        return new ResultObj<>(ResultStatusEnum.ERROR_SQL, e.getMessage());
    }
}