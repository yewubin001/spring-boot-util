package com.example.springboot.design.simplefactory;

/**
 * @Auther: yewub
 * @Date: 2019/2/15 17:03
 * @Description:
 * 简单工厂模式（静态工厂模式）
 * 简单工厂模式是创建型模式，创建型模式顾名思义，
 * 也就是说在创建对象的时候，遇到了瓶颈才会选择的设计模式。
 * 假如新添了产品类就得修改工厂类，这样就会违反开闭原则。
 */
public interface Operation {
    int operat(int num1, int num2);
}
