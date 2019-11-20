package com.project.config;

import com.project.constant.BusinessStatus;
import common.exception.BusinessException;
import common.result.ResultObj;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.sql.SQLException;
import javax.servlet.ServletException;

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
    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public ResultObj<?> handlerProjectException(BusinessException e) {
        logger.info(e.getMsg());
        return new ResultObj<>(e.getStatus(), e.getMsg());
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
            return new ResultObj<>(BusinessStatus.URL_METHOD_EOOR.status, e.getMessage());
        } else if (e instanceof NoHandlerFoundException) {
            // 404 请求未找到
            return new ResultObj<>(BusinessStatus.URL_NOT_FOUND.status, e.getMessage());
        } else {
            return new ResultObj<>(BusinessStatus.ERROR.status, e.getMessage());
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
        return new ResultObj<>(BusinessStatus.ERROR_SQL.status, e.getMessage());
    }
}