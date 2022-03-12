package com.example.springboot.java8.generic;

/**
 * @description:
 * @author: yewubin
 * @date: 2021/8/5 17:26
 * @version: v1.0
 */
//定义一个泛型接口

public interface GenericInterface<T> {
    T next();
}

class FruitGeneric<T> implements GenericInterface<T> {

    @Override
    public T next() {
        return null;
    }
}

class waterGeneric implements GenericInterface<String> {

    @Override
    public String next() {
        return null;
    }
}