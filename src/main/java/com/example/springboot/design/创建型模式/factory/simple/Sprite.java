package com.example.springboot.design.factory.simple;

/**
 * @version : 1.0
 * @Description: 汽水
 * @Auther: ywb
 * @Date: 2020/10/31 10:31
 */
public class Sprite extends Drinks {

    @Override
    protected void produce() {
        System.out.println("drink sprite");
    }
}
