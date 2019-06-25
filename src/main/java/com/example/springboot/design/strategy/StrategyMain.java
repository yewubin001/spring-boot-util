package com.example.springboot.design.strategy;

/**
 * @Auther: yewub
 * @Date: 2019/2/15 17:11
 * @Description: 实际上在通常的做法中，这两种是可以结合起来的，利用工厂模式来创建对象
 */
public class StrategyMain {

    public static void main(String[] args) {
        int num1 = 100;
        int num2 = 200;

        Context context = new Context(new AddStrategy());
        context.executeOperate(num1, num2);

        Context context1 = new Context(new SubtractionStrategy());
        context1.executeOperate(num2, num1);
    }
}
