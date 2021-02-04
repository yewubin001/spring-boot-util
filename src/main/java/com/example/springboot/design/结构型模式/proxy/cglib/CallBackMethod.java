package com.example.springboot.design.结构型模式.proxy.cglib;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Auther: 59315
 * @Date: 2021/2/4 22:09
 * @Description: 
 */
public class CallBackMethod implements MethodInterceptor {

    /**
     *
     * @param o 代理对象
     * @param method 目标对象中的方法
     * @param objects 目标对象方法的参数
     * @param methodProxy 代理对象中的方法对象
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("begin...");
        Object obj = methodProxy.invokeSuper(o, objects);
        System.out.println("end...");
        return obj;
    }
}
