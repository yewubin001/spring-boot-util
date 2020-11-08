package com.example.springboot.design.decorator;

/**
 * 基类。通常是一个抽象类或者一个接口，定义了属性或者方法，方法的实现可以由子类实现或者自己实现。通常不会直接使用该类，
 * 而是通过继承该类来实现特定的功能，它约束了整个继承树的行为。
 */
public abstract class Person {
    String description = "Unkonwn";

    public String getDescription() {
        return description;
    }

    /**
     * @return
     */
    public abstract double cost();
}