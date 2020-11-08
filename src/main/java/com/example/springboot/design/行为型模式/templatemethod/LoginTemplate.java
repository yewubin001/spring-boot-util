package com.example.springboot.design.templatemethod;

/**
 * 模板方法模式的优点:
 *
 * 封装不可变部分，扩展可变部分
 * 提取公共代码，便于维护
 * 行为由父类控制，子类实现
 */
public abstract class LoginTemplate {

    abstract void getLoginPage();

    abstract void setUserAndPass();

    abstract void clickLogin();

    abstract void home();

    /**
     * 在模板中定义了一个运行方法
     * 这个方法会按照我么需要的顺序去执行
     */
    public final void login(){
        getLoginPage();
        setUserAndPass();
        clickLogin();
        home();
        System.out.println("loging ...");
    }
}
