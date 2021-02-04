package com.example.springboot.design.结构型模式.proxy.cglib;

import org.springframework.cglib.proxy.Enhancer;

/**
 * @Auther: 59315
 * @Date: 2021/2/4 22:07
 * @Description: 
 */
public class CglibTest {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();

        enhancer.setSuperclass(Student.class);

        CallBackMethod callBackMethod = new CallBackMethod();
        enhancer.setCallback(callBackMethod);
        Student o = (Student)enhancer.create();
        o.study();
    }
}
