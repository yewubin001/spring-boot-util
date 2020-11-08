package com.example.springboot.design.decorator;

/**
 *  它是装饰者共同实现的抽象类（也可以是接口）。
 *  比如说，Decorator代表衣服这一类装饰者，那么它的子类应该是T恤、裙子这样的具体的装饰者。
 */
public abstract class HatDecorator extends Person {

    @Override
    public abstract String getDescription();

}
