package com.example.springboot.java8.generic;

/**
 * @Auther: 59315
 * @Date: 2021/2/5 21:57
 * @Description: 如果不知道name和address的类型的时候，使用类型占位符进行占位
 * 定义一个泛型类
 */
public class Person<T> {

    private T name;
    private T address;

    public T getName() {
        return name;
    }

    public void setName(T name) {
        this.name = name;
    }

    public T getAddress() {
        return address;
    }

    public void setAddress(T address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name=" + name +
                ", address=" + address +
                '}';
    }

    // 通配符方法：可以传入所有类行的p
    public void show(Person<?> p){
        this.setName((T)p.getName());
    }

    // 上边界
    public void show2(Person<? super T> p) {
        this.setName((T) p.getName());
    }

}

class Person1<K, V> {
    private K name;
    private V address;

    public K getName() {
        return name;
    }

    public void setName(K name) {
        this.name = name;
    }

    public V getAddress() {
        return address;
    }

    public void setAddress(V address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person1{" +
                "name=" + name +
                ", address=" + address +
                '}';
    }
}

class Person2<T> {

    //普通方法使用类的占位符
    public void show(T name) {
        System.out.println(name + "正在演讲！");
    }

    // 普通方法自定义泛型占位符
    public <M> void show1(M name) {
        System.out.println(name + "正在演讲！");
    }

    // 静态泛型方法中的类型占位符和类中的泛型占位符没有关系
    // 静态泛型方法中的类型占位符属于类的，而类中的泛型占位符属于对象的
    public static <W> void show2(W name) {
        System.out.println(name + "静态方法正在演讲！");
    }

    public static <E> E show3(E name) {
        return name;
    }
}