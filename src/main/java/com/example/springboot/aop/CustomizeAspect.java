package com.example.springboot.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author yewub
 * @Date: 2019/5/25 12:51
 * @Description: 切面类
 * @Aspect 注解是切面注解类
 * @Pointcut 切点定义
 * @Before 是方法执行前调用
 * @After 是方法执行后调用
 * @AfterReturning 方法执行返回值调用
 */
@Aspect
@Component
@Order(0)
public class CustomizeAspect {

    private final Logger log = LoggerFactory.getLogger(CustomizeAspect.class);

    /**
     * 1、com.example.springboot.controller 包下面的任意类的任意方法任意参数
     * @Pointcut("execution(com.example.springboot.controller.*.*(..))")
     *
     * 定义切入点为 带有 EnabledUserAccess 注解的，如下
     */
    @Pointcut("@annotation(com.example.springboot.aop.EnabledUserAccess)")
    public void execute() {

    }

    /**
     * 环绕通知： 环绕通知非常强大，可以决定目标方法是否执行，
     * 什么时候执行，执行时是否需要替换方法参数，执行完毕是否需要替换返回值。
     * 环绕通知第一个参数必须是org.aspectj.lang.ProceedingJoinPoint类型
     */
    @Around("execute()")
    public Object doAroundAdvice(ProceedingJoinPoint point) {
        log.info("获取参数：{}", point.getArgs()[0]);
        EnabledUserAccess userAccess = ((MethodSignature) point.getSignature()).getMethod().getAnnotation(EnabledUserAccess.class);
        log.info("环绕通知被执行，目标方法执行之前,value={},needLogin={}", userAccess.value(), userAccess.needLogin());
        try {
            //执行方法
            Object obj = point.proceed();
            log.info("环绕通知被执行，目标方法执行之后");
            return obj;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

}
