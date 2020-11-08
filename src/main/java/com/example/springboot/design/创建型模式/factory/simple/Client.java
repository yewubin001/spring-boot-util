package com.example.springboot.design.factory.simple;

/**
 * @version : 1.0
 * @Description: TODO
 * @Auther: ywb
 * @Date: 2020/10/31 10:37
 */
public class Client {

    public static void main(String[] args) {
        DrinksFactory drinksFactory = new DrinksFactory();
        Drinks drinks = drinksFactory.produceDrink(Cola.class);
        drinks.produce();
    }

}
