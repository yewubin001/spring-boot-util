package com.example.springboot.design.结构型模式.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Auther: yewub
 * @Date: 2019/2/14 17:18
 * @Description:
 */
public class MyInvocationHandler implements InvocationHandler {

    private Object target;

    public MyInvocationHandler(Object target) {
        this.target = target;
    }

    /**
     *
     * @param proxy：生成的代理对象（不用）
     * @param method：目标对象中的方法
     * @param args：是该方法的参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("proxy:"+proxy.getClass().getName());
        System.out.println("Do something before"); //前置通知
        Object result = method.invoke(target, args);
        System.out.println("Do something after");//后置通知
        return result;
    }


    public <T> T  getProxy() {
        return  (T) Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), this);
    }
}
