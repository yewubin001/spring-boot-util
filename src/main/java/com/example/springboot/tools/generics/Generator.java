package com.example.springboot.tools.generics;

/**
 * @Auther: yewub
 * @Date: 2019/1/12 18:52
 * @Description:
 */
//定义一个泛型接口
public interface Generator<T> {

    T next();
}