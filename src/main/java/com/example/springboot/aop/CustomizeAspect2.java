package com.example.springboot.aop;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

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
@Order(1)
public class CustomizeAspect2 {

    private final Logger log = LoggerFactory.getLogger(CustomizeAspect2.class);

    @AfterReturning(value = "@annotation(userAccess)", returning = "list")
    public void doAfterReturning(EnabledUserAccess userAccess, List<Object> list) {
        log.info("needLogin={}, value={}",userAccess.needLogin(),userAccess.value());
        log.info(list.toString());
    }
}
