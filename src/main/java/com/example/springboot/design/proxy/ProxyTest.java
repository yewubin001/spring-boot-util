package com.example.springboot.design.proxy;

/**
 * @Auther: yewub
 * @Date: 2019/2/14 17:22
 * @Description:
 */
public class ProxyTest {

    public static void main(String[] args) {

        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        //System.out.println(Proxy.getProxyClass(Subject.class.getClassLoader(), Subject.class));

        Subject subject = new RealSubject();
        MyInvocationHandler myInvocationHandler = new MyInvocationHandler(subject);
        Subject proxy = myInvocationHandler.getProxy();
        proxy.doSomething();
    }
}
