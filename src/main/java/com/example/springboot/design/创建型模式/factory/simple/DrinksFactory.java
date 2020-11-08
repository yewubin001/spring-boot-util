package com.example.springboot.design.factory.simple;

/**
 * @author Magfin
 * @version : 1.0
 * @Description: 静态工厂
 * @Date: 2020/10/31 10:33
 */
public class DrinksFactory {

    public Drinks produceDrink(Class clazz) {
        try {
            return (Drinks)Class.forName(clazz.getName()).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return  null;
    }

}
