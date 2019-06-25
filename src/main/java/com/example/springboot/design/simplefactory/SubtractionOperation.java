package com.example.springboot.design.simplefactory;

/**
 * @Auther: yewub
 * @Date: 2019/2/15 17:06
 * @Description:
 */
public class SubtractionOperation implements Operation {
    @Override
    public int operat(int num1, int num2) {
        return num1 - num2;
    }
}
