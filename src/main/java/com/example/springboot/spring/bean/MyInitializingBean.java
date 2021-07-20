package com.example.springboot.spring.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 先执行InitializingBean的afterPropertiesSet方法，而后执行init-method方法
 */
public class MyInitializingBean implements InitializingBean, DisposableBean {

    // 生命周期回调方法
    @Override
    public void afterPropertiesSet() {
        System.out.println("-------生命周期回调方法afterPropertiesSet--------");
        // 如果调用afterPropertiesSet方法时出错，则不调用init-method指定的方法
        // String string = null;
        //string.length();
    }

    private void initMethod() {
        System.out.println("生命周期回调方法 init-Method ...");
    }

    private void destroyMethod(){
        System.out.println("destroy-Method...");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("destroy ...");
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("-------生命周期回调方法postConstruct--------");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("--------preDestory-------");
    }
}
