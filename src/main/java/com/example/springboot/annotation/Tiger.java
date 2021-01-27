package com.example.springboot.annotation;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @version : 1.0
 * @Description: TODO
 * @Auther: ywb
 * @Date: 2020/6/11 9:29
 */
public class Tiger implements InitializingBean, DisposableBean {

    public Tiger(){
        System.out.println("new Tiger()");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("destroy...");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet...");
    }

    @PostConstruct
    public void initPortContruct(){
        System.out.println("@initPortContruct...");
    }

    @PreDestroy
    public void preDestroy(){
        System.out.println("@preDestroy...");
    }

    public void init(){
        System.out.println("init...");
    }

    public void destroyInit() {
        System.out.println("destroyInit...");
    }
}
