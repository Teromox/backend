package com.sungjujjang.ter.global;

import jakarta.servlet.http.HttpServletRequest;
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
public class AuthAOP {
    @Pointcut("execution(* com.sungjujjang.ter..*Controller.*(..))")
    private void cut(){}

    @Before("cut() && !@annotation(com.sungjujjang.ter.global.NoAuthAnno)")
    public void beforeParameterLog(JoinPoint joinPoint) {
        System.out.println("Requested");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();

            String jwtToken = request.getHeader("Authorization");
            System.out.println("jwtToken value = " + jwtToken);
        }

        Object[] args = joinPoint.getArgs();
        if (args.length <= 0) System.out.println("no parameter");
        for (Object arg : args) {
            if (arg != null) {
                System.out.println("parameter type = " + arg.getClass().getSimpleName());
                System.out.println("parameter value = " + arg);
            }
        }
    }
}
