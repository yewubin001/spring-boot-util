package com.example.springboot.design.结构型模式.adapter;

/**
 * @version : 1.0
 * @Description: TODO
 * @Auther: ywb
 * @Date: 2020/11/9 15:09
 */
public class Client {

    public static void main(String[] args) {
        AdapterByClass adapterByClass = new AdapterByClass();
        adapterByClass.request();


        System.out.println("-----------");

        Adaptee adaptee = new Adaptee();
        AdapterByObject adapterByObject = new AdapterByObject(adaptee);
        adapterByObject.request();
    }

}
