package com.example.springboot.design.simplefactory;

/**
 * @Auther: yewub
 * @Date: 2019/2/15 17:11
 * @Description:
 */
public class FactoryMain {

    public static void main(String[] args) {
        int num1 = 100;
        int num2 = 200;
        //加法
        Operation op = FactoryUtils.createOperation("+");
        int result = op.operat(num1, num2);
        System.out.println(result);
        //减法
        op = FactoryUtils.createOperation("-");
        result = op.operat(num1, num2);
        System.out.println(result);
    }
}
