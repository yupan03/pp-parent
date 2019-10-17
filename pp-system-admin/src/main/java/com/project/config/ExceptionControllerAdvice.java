package com.project.config;

import java.sql.SQLException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.project.constant.BusinessStatus;

import common.exception.BusinessException;
import common.result.Result;

/**
 * 统一异常处理
 * 
 * @author David
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
    public Result handlerProjectException(BusinessException e) {
        logger.info(e.getMsg());
        return new Result(e.getStatus(), e.getMsg());
    }

    /**
     ** 请求异常处理 配置文件增加 spring:mvc:throw-exception-if-no-handler-found: true
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
            return new Result(HttpStatus.BAD_REQUEST.value(), e.getMessage());
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
    public Result handlerSQLException(SQLException e) {
        return new Result(BusinessStatus.ERROR_SQL.status, e.getMessage());
    }

    /**
     * 验证框架异常
     * 
     * @param e
     * @return
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public Result handleVilolationException(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        StringBuilder strBuilder = new StringBuilder();
        for (ConstraintViolation<?> violation : violations) {
            strBuilder.append(violation.getMessage() + "\n");
        }
        return new Result(BusinessStatus.ERROR_PARAM.status, strBuilder.toString());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result handMethodAraumentException(MethodArgumentNotValidException e) {
        BindingResult result = e.getBindingResult();

        FieldError error = result.getFieldError();
        String field = error.getField();
        String code = error.getDefaultMessage();
        String message = String.format("%s %s", field, code);

        return new Result(BusinessStatus.ERROR_PARAM.status, message);
    }

}