package com.example.springboot.design.templatemethod;

public class QQLogin extends LoginTemplate {

    @Override
    public void getLoginPage() {
        System.out.println("跳转到QQ登录页面...");
    }

    @Override
    public void setUserAndPass() {
        System.out.println("设置好了QQ的用户名和密码...");
    }

    @Override
    public void clickLogin() {
        System.out.println("点击了QQ的登录按钮...");
    }

    @Override
    public void home() {
        System.out.println("欢迎来到QQ...");
    }

}
