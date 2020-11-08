package com.example.springboot.design.创建型模式.singleton;


/**
 * @Auther: 59315
 * @Date: 2020/9/29 21:33
 * @Description:
 * Effective Java作者Josh Bloch 提倡的方式，不仅可以解决线程同步，还可以防止反序列化
 * 因为枚举没有构造方法
 */
public enum Singleton4 {

    INSTANT;

    public static void main(String[] args) {
        System.out.println(Singleton4.INSTANT.hashCode());
        System.out.println(Singleton4.INSTANT.hashCode());
    }
}