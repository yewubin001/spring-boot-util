package com.example.springboot.design.strategy;

/**
 * @Auther: yewub
 * @Date: 2019/2/15 17:16
 * @Description:
 * 策略模式是定义了一系列的算法，把这些算法封装起来，使他们之间可以相互替换，
 * 这些算法独立于他的客户端并且可以相互变化，其实就是客户不用不用关心他们是怎么实现的，
 *
 * 对开闭原则完美支持
 *
 *  策略模式注重的是行为算法，而工厂模式注重的是对象的创建
 *
 *  Comparator 就是策略模式
 */
public interface Strategy {
    int operat(int num1, int num2);
}
