package redis.annotation;

import java.lang.annotation.*;

/**
 * 防止重复提交
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NoRepeatSubmit {
}