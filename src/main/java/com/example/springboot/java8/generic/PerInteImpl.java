package com.example.springboot.java8.generic;

/**
 * @Auther: 59315
 * @Date: 2021/2/5 22:27
 * @Description:
 * 1、泛型接口的实现类可以指定具体的泛型类型
 * 2、泛型接口的实现类如果没有指定具体的泛型类型，必须要在这个实现类中声明一个泛型类型占位符给接口用
 *
 */
public class PerInteImpl<T> implements PerInte<T> {
    @Override
    public void show(T name) {
        System.out.println("泛型接口：" + name);
    }
}
