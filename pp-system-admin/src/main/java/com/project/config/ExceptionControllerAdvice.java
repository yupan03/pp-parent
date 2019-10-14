package com.project.config;

import java.sql.SQLException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import common.result.Result;
import common.result.exception.ResultException;
import common.result.status.ResultStatusEnum;

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
    @ExceptionHandler(value = ResultException.class)
    @ResponseBody
    public Result handlerProjectException(ResultException e) {
        logger.info(e.getMsg());
        return new Result(e.getStatusEnum().status, e.getMsg());
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
            return new Result(ResultStatusEnum.URL_METHOD_EOOR.status, e.getMessage());
        } else if (e instanceof NoHandlerFoundException) {
            // 404 请求未找到
            return new Result(ResultStatusEnum.URL_NOT_FOUND.status, e.getMessage());
        } else {
            return new Result(ResultStatusEnum.ERROR.status, e.getMessage());
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
        return new Result(ResultStatusEnum.ERROR_SQL.status, e.getMessage());
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
        return new Result(ResultStatusEnum.ERROR_PARAM.status, strBuilder.toString());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result handMethodAraumentException(MethodArgumentNotValidException e) {
        BindingResult result = e.getBindingResult();

        FieldError error = result.getFieldError();
        String field = error.getField();
        String code = error.getDefaultMessage();
        String message = String.format("%s %s", field, code);

        return new Result(ResultStatusEnum.ERROR_PARAM.status, message);
    }

}