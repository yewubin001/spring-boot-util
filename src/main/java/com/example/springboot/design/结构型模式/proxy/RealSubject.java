package com.example.springboot.design.结构型模式.proxy;

/**
 * @Auther: yewub
 * @Date: 2019/2/14 17:16
 * @Description:
 */
public class RealSubject implements Subject {

    @Override
    public void doSomething() {
        System.out.println("RealSubject do something");
    }
}
