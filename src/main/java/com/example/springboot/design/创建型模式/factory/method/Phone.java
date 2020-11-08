package com.example.springboot.design.factory.method;

/**
 * @version : 1.0
 * @Description: TODO
 * @Auther: ywb
 * @Date: 2020/10/31 13:50
 */
public class Phone extends Electronic {
    @Override
    void name() {
        System.out.println("phone");
    }
}
