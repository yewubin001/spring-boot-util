package com.example.springboot.design.创建型模式.singleton;
/**
 * @Auther: 59315
 * @Date: 2020/10/20 23:23
 * @Description:  饿汉模式
 */
public class Singleton {

    private static Singleton singleton;

    static {
        singleton = new Singleton();
    }

    // 私有化构造方法
    private Singleton(){
    }

    public static Singleton getSingleton(){
        return singleton;
    }

    public static void main(String[] args) {
        System.out.println(getSingleton().hashCode());
        System.out.println(getSingleton().hashCode());
    }
}
