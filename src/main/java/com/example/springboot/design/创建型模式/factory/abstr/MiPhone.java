package com.example.springboot.design.factory.abstr;

/**
 * @version : 1.0
 * @Description: TODO
 * @Auther: ywb
 * @Date: 2020/10/31 14:39
 */
public class MiPhone extends Phone {

    @Override
    void call() {
        System.out.println("call miPhone");
    }
}
