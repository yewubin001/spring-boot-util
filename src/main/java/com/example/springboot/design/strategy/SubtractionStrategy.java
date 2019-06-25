package com.example.springboot.design.strategy;

/**
 * @Auther: yewub
 * @Date: 2019/2/15 17:18
 * @Description:
 */
public class SubtractionStrategy implements Strategy {

    @Override
    public int operat(int num1, int num2) {
        return num1 - num2;
    }
}
