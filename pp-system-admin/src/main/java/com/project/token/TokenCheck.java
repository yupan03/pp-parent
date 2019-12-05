package com.project.token;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Token校验注解，所有的接口都需要token检验，防止单独访问子系统（不经过路由管理器）不安全性
 *
 * @author David
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface TokenCheck {
    boolean value() default true;
}