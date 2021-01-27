package com.example.springboot.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Auther: yewub
 * @Date: 2019/5/25 12:28
 * @Description: 自定义注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EnabledUserAccess {


    /**
     * 允许访问的角色，默认不限角色
     *
     * @return
     */
    String[] value() default "";

    /**
     * 访问是否需要登录，默认需要登录
     * @return
     */
    boolean needLogin() default true;
}
