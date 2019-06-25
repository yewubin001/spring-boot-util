package com.example.springboot.design.simplefactory;

/**
 * @Auther: yewub
 * @Date: 2019/2/15 17:07
 * @Description:
 */
public class FactoryUtils {

    public static Operation createOperation(String op) {
        Operation operation = null;
        switch (op) {
            case "+":
                operation = new AddOperation();
                break;
            case "-":
                operation = new SubtractionOperation();
                break;
            default:
                break;
        }
        return operation;
    }
}
