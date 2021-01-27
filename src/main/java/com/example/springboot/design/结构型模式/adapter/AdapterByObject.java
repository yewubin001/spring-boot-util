package com.example.springboot.design.结构型模式.adapter;

/**
 * @version : 1.0
 * @Description: TODO
 * @Auther: ywb
 * @Date: 2020/11/9 15:15
 */
public class AdapterByObject implements Target{

    private Adaptee adaptee;

    public AdapterByObject(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void request() {
        System.out.println("begin....");
        adaptee.specificRequest();
        System.out.println("end...");
    }
}
