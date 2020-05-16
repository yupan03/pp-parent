package com.pp.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RepeatSubmitAspect {
    @Pointcut("@annotation(redis.annotaion.NoRepeatSubmit)")
    public void tokenCut() {
    }
}