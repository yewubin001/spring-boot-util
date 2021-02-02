package com.example.springboot.design.结构型模式.proxy;

import java.lang.reflect.Proxy;

/**
 * @Auther: yewub
 * @Date: 2019/2/14 17:22
 * @Description:
 */
public class ProxyTest {

    public static void main(String[] args) {

        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        //System.out.println(Proxy.getProxyClass(Subject.class.getClassLoader(), Subject.class));

        // 创建目标对象
        Subject subject = new RealSubject();
        MyInvocationHandler handler = new MyInvocationHandler(subject);
        /**
         * Proxy.newProxyInstance() 方法的三个参数：
         * 1、loader：类加载器（类的字节码对象获得），写法固定
         * 2、interfaces：得到该对象实现所有接口的字节码对象数组，写法固定
         * 3、需要实现一个InvocationHandler接口的对象，需要自己实现，对目标方法的增强就是在这个方法中实现的。
         */
        Subject proxy = (Subject)Proxy.newProxyInstance(subject.getClass().getClassLoader(), subject.getClass().getInterfaces(), handler);
//        Subject proxy = handler.getProxy();
        proxy.doSomething();
    }
}
