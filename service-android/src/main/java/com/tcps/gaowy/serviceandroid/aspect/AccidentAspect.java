package com.tcps.gaowy.serviceandroid.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class AccidentAspect {

    @Around(value = "execution(* com.tcps.gaowy.serviceandroid.controller.*.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint point) throws Throwable {
        Object[] objects = point.getArgs();
        Object o = point.proceed();
        return o;
    }
}
