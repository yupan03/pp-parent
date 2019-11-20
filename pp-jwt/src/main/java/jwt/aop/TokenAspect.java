package jwt.aop;

import common.exception.BusinessException;
import jwt.JwtUtil;
import jwt.LoginAccount;
import jwt.annotaion.TokenCheck;
import jwt.constant.TokenType;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import javax.servlet.http.HttpServletRequest;

/**
 * Token前置增强
 * 
 * @author David
 *
 */
@Aspect
public class TokenAspect {

    /**
     ** 定义切面
     */
    @Pointcut("@annotation(jwt.annotaion.TokenCheck)")
    public void tokenCut() {

    }

    @Before("tokenCut()")
    public void around(JoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();

        if (method.isAnnotationPresent(TokenCheck.class)) {

            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                    .getRequest();// 获取request

            String token = request.getHeader(HttpHeaders.AUTHORIZATION);

            if (StringUtils.isEmpty(token)) {
                throw new BusinessException(HttpStatus.UNAUTHORIZED.value(), "请登录");
            }

            // 检验token的失效性
            LoginAccount account = new JwtUtil().getAccountFromToken(token);
            if (account == null) {
                throw new BusinessException(HttpStatus.UNAUTHORIZED.value(), "非法token");
            } else if (account.getTokenType() == TokenType.OVERDUE) {
                throw new BusinessException(HttpStatus.UNAUTHORIZED.value(), "token失效，请重新登录");
            }
        }
    }
}