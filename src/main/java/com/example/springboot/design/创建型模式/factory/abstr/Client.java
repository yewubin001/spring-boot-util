package com.example.springboot.design.factory.abstr;

/**
 * @version : 1.0
 * @Description: TODO
 * @Auther: ywb
 * @Date: 2020/10/31 15:45
 */
public class Client {

    public static void main(String[] args) {
        AppleFactory appleFactory = new AppleFactory();
        Computer computer = appleFactory.produceComputer();
        computer.work();

        Phone phone = appleFactory.producePhone();
        phone.call();
    }

}
