package com.example.springboot.spring.reflect;

import java.lang.annotation.*;

/**
 * @author 59315
 * @Date: 2020/5/24 21:48
 * @Description:  自定义注解
 */
// 四个元注解
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Inherited
@Documented
public @interface AutoWire {

}
