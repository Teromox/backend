package com.sungjujjang.ter.global;

import com.sungjujjang.ter.global.error.exception.NotValidJwtErr;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.context.request.RequestContextHolder;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.util.Arrays;

@Aspect
@Component
@RequiredArgsConstructor
public class AuthAOP {
    private final JWTSetting jwtSetting;

    @Pointcut("execution(* com.sungjujjang.ter..*Controller.*(..))")
    private void cut(){}

    @Before("cut() && !@annotation(com.sungjujjang.ter.global.NoAuthAnno)")
    public void beforeParameterLog(JoinPoint joinPoint) {
        System.out.println("Requested");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();

            String jwtToken = request.getHeader("Authorization");
            String userId = jwtSetting.checkToken(jwtToken);
            request.setAttribute("userId", userId);
        } else {
            throw NotValidJwtErr.EXCEPTION;
        }
    }
}
