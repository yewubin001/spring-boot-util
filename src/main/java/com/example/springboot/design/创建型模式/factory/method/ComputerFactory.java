package com.example.springboot.design.factory.method;

/**
 * @version : 1.0
 * @Description: TODO
 * @Auther: ywb
 * @Date: 2020/10/31 13:52
 */
public class ComputerFactory implements ElectronicFactory {

    @Override
    public Electronic produce(Class clazz) {
        if (clazz.equals(Computer.class)) {
            return new Computer();
        }
        return null;
    }
}
