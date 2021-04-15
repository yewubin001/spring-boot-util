package com.example.springboot.design.创建型模式.singleton;

import java.lang.reflect.Constructor;

/**
 * @Auther: 59315
 * @Date: 2020/9/29 21:33
 * @Description:  懒汉模式-双重锁，懒加载
 */
public class Singleton2 {
    // 此变量需要用valotile修饰防止指令重排序
    private static volatile Singleton2 singleton = null;
    // 私有化构造方法
    private Singleton2(){
        if(singleton != null) {
            throw new RuntimeException("Can not do this");
        }
    }
    public static Singleton2 getInstance(){
        // 进入方法内，先判断实例是否为空，以确定是否需要同步代码块
        if(singleton == null) {
            synchronized(Singleton2.class){
                // 进入同步代码块同事再次判断实例是否为空
                if(singleton == null){
                    singleton = new Singleton2();
                }
            }
        }
        return singleton;
    }


    public static void main(String[] args) throws Exception {
        Singleton2 instance = Singleton2.getInstance();
        System.out.println(instance.hashCode());

        Class<?> aClass = Class.forName("com.example.springboot.design.创建型模式.singleton.Singleton");
        // Object o = aClass.newInstance();
        // System.out.println(o.hashCode());

        // getDeclaredConstructor(Class<?>... parameterTypes) 返回指定参数类型的所有构造器，
        // 包括public的和非public的，当然也包括private的。
        // getConstructor(Class<?>... parameterTypes) 只返回制定参数类型访问权限是public的构造器。
        Constructor<?> constructor = aClass.getDeclaredConstructor();

        //constructor.setAccessible(true);  用反射时访问私有变量
        Singleton2 singleton = (Singleton2) constructor.newInstance();
        System.out.println(singleton.hashCode());
    }
}