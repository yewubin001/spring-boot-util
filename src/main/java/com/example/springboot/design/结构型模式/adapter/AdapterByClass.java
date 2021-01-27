package com.example.springboot.design.结构型模式.adapter;

/**
 * @version : 1.0
 * @Description: TODO
 * @Auther: 类的适配器
 * @Date: 2020/11/9 15:08
 */
public class AdapterByClass extends Adaptee implements Target {
    @Override
    public void request() {
        System.out.println("begin...");
        super.specificRequest();
        System.out.println("end....");
    }
}



