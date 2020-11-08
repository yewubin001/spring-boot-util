package com.example.springboot.design.decorator;

/**
 * 充当了“被装饰者”的角色。
 */
public class Teenager extends Person {

    public Teenager() {
        description = "Shopping List:";
    }

    @Override
    public double cost() {
        //什么都没买，不用钱
        return 0;
    }

}