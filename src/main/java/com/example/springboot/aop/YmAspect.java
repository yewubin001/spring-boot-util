package com.example.springboot.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class YmAspect {

    private final Logger log = LoggerFactory.getLogger(YmAspect.class);

    @Pointcut("execution(* com.example.springboot.controller.*.*(..))")
    public void execute() {

    }

    @Around("execute()")
    public Object doAroundAdvice(ProceedingJoinPoint point) {
        long start = System.currentTimeMillis();
        try {
            //执行方法
            Object obj = point.proceed();
            Thread.sleep(2000);
            System.out.println("执行时间为："+(System.currentTimeMillis() - start));
            return obj;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }



}
