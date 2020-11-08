package com.example.springboot.design.factory.abstr;

/**
 * @version : 1.0
 * @Description: TODO
 * @Auther: ywb
 * @Date: 2020/10/31 15:18
 */
public class MiFactory implements AbstractFactory {

    @Override
    public Phone producePhone() {
        return new MiPhone();
    }

    @Override
    public Computer produceComputer() {
        return new MiComputer();
    }
}
