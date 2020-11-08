package com.example.springboot.design.创建型模式.singleton;


/**
 * @Auther: 59315
 * @Date: 2020/9/29 21:33
 * @Description:
 * 静态内部类方式，JVM保证单例（JVM只加载一次class）
 * 加载外部类时不会加载内部类，这样可以实现懒加载
 * 因为SingletonHolder类没有被主动使用，只有显示通过调用getInstance方法时，才会显示装载SingletonHolder类，从而实例化instance。
 */
public class Singleton3 {

    private static class SingletonHolder {
        private static final Singleton3 INSTANCE = new Singleton3();
    }

    // 私有化构造方法
    private Singleton3(){
    }

    public static Singleton3 getInstant(){
        return SingletonHolder.INSTANCE;
    }

    public static void main(String[] args) {
        System.out.println(getInstant().hashCode());
        System.out.println(getInstant().hashCode());
    }
}