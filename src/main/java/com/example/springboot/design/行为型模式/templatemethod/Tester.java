package com.example.springboot.design.templatemethod;

public class Tester {

    public static void main(String[] args) {
        LoginTemplate qq = new QQLogin();
        qq.login();

        System.out.println("-----------------------");

        LoginTemplate weixin = new WeixinLogin();
        weixin.login();
    }

}
