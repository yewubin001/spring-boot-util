package com.example.springboot.design.strategy;

/**
 * @Auther: yewub
 * @Date: 2019/2/15 17:19
 * @Description:
 */
public class Context {

    private Strategy strategy;

    public Context(Strategy strategy){
        this.strategy = strategy;
    }

    public void executeOperate(int num1, int num2){
        System.out.println(strategy.operat(num1, num2));
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }
}
