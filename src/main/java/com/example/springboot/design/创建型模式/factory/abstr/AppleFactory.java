package com.example.springboot.design.factory.abstr;

/**
 * @author Magfin
 * @version : 1.0
 * @Description: TODO
 * @Date: 2020/10/31 15:18
 */
public class AppleFactory implements AbstractFactory {

    @Override
    public Phone producePhone() {
        return new IPhone();
    }

    @Override
    public Computer produceComputer() {
        return new MacComputer();
    }
}
