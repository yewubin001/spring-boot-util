package com.example.springboot.idempotent;
/**
 * @Auther: 59315
 * @Date: 2019/11/14 23:43
 * @Description:
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 在需要保证 接口幂等性 的Controller的方法上使用此注解
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiIdempotent {
}