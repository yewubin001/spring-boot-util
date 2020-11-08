package com.example.springboot.design.factory.abstr;

/**
 * @version : 1.0
 * @Description: TODO
 * @Auther: ywb
 * @Date: 2020/10/31 15:16
 */
public interface AbstractFactory {

    Phone producePhone();

    Computer produceComputer();

}
