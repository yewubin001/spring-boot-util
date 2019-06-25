package com.example.springboot.tools.generics;

/**
 * @Auther: yewub
 * @Date: 2019/1/12 15:31
 * @Description: 泛型类
 * 此处T可以随便写为任意标识，常见的如T、E、K、V等形式的参数常用于表示泛型
 * 在实例化泛型类时，必须指定T的具体类型
 */

public class Generic<T> implements Generator<T> {

    private T key;

    public Generic(T key) {
        this.key = key;
    }

    public T getKey() {
        return key;
    }

    public static void main(String[] args) {
        Generic<Integer> genericInteger = new Generic<>(123456);
        Generic<String> genericString = new Generic<>("key_vlaue");
        System.out.println("泛型测试,key is " + genericInteger.getKey());
        System.out.println("泛型测试,key is " + genericString.getKey());
    }

    @Override
    public T next() {
        return null;
    }
}
