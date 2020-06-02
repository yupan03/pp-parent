package com.pp.config;

import com.pp.common.exception.BusinessException;
import com.pp.common.result.Result;
import com.pp.constant.BusinessStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.ServletException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Set;

/**
 * 统一异常处理
 *
 * @author David
 */
//@RestControllerAdvice
public class ExceptionControllerAdvice {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 其他异常处理
     */
    @ExceptionHandler(value = Exception.class)
    public Result handlerException(Exception e) {
        return new Result(HttpStatus.SERVICE_UNAVAILABLE.value(), e.getMessage());
    }

    /**
     * 自定义系统业务异常处理
     */
    @ExceptionHandler(value = BusinessException.class)
    public Result handlerProjectException(BusinessException e) {
        logger.info(e.getMsg());
        return new Result(e.getStatus(), e.getMsg());
    }

    /**
     * 请求异常处理 配置文件增加 spring:mvc:throw-exception-if-no-handler-found: true
     */
    @ExceptionHandler(value = ServletException.class)
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
     * 数据库异常处理
     */
    @ExceptionHandler(value = SQLException.class)
    public Result handlerSQLException(SQLException e) {

        if (e instanceof SQLIntegrityConstraintViolationException) {
            e.printStackTrace();
            return SQLIntegrityConstraintViolationException(e.getMessage());
        }
        return new Result(BusinessStatus.ERROR_SQL.status, e.getMessage());
    }

    // 数据库统一判重处理
    private Result SQLIntegrityConstraintViolationException(String message) {
        // 数据库表中索引名称唯一
        if (message.contains("user_UN")) {
            return new Result(BusinessStatus.ERROR_SQL.status, "用户账号重复");
        } else {
            return new Result(BusinessStatus.ERROR_SQL.status, "主键重复");
        }
    }

    /**
     * 验证框架异常
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public Result handleViolationException(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        StringBuilder strBuilder = new StringBuilder();
        for (ConstraintViolation<?> violation : violations) {
            strBuilder.append(violation.getMessage() + "\n");
        }
        return new Result(BusinessStatus.ERROR_PARAM.status, strBuilder.toString());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result handMethodArgumentException(MethodArgumentNotValidException e) {
        BindingResult result = e.getBindingResult();

        FieldError error = result.getFieldError();
        String field = error.getField();
        String code = error.getDefaultMessage();
        String message = String.format("%s %s", field, code);

        return new Result(BusinessStatus.ERROR_PARAM.status, message);
    }
}