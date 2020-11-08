package com.example.springboot.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 元注解
 * @Target 是这个注解的作用域，ElementType.METHOD是这个注解的作用域的列表 CONSTRUCTOR（构造方法声明）,
 *      FIELD（字段声明）,LOCAL VARIABLE（局部变量声明）,METHOD（方法声明）,PACKAGE（包声明）,PARAMETER（参数声明）,TYPE（类接口）
 * @Retention 是它的生命周期  SOURCE（只在源码显示，编译时丢弃）,CLASS（编译时记录到class中，运行时忽略）,
 *      RUNTIME（运行时存在，可以通过反射读取）
 * @Inherited 是一个标识性的元注解，它允许子注解继承它。
 * @Documented 生成javadoc时会包含注解。
 *
 * @Auther: yewub
 * @Date: 2018/11/9 10:37
 * @Description:
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomAnnotation {

    boolean require() default true;

    String info() default "";

}
