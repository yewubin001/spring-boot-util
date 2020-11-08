package com.example.springboot.java8;

/**
 * @Auther: 59315
 * @Date: 2020/10/22 22:52
 * @Description:
 */
@FunctionalInterface
public interface Converter<F, T> {
    T convert(F from);
}