package com.example.springboot.design.factory.method;

/**
 * @version : 1.0
 * @Description: TODO
 * @Auther: ywb
 * @Date: 2020/10/31 14:02
 */
public class Client {

    public static void main(String[] args) {
        ComputerFactory computerFactory = new ComputerFactory();
        Electronic computer = computerFactory.produce(Computer.class);
        computer.name();


        PhoneFactory phoneFactory = new PhoneFactory();
        Electronic phone = phoneFactory.produce(Phone.class);
        phone.name();
    }

}
