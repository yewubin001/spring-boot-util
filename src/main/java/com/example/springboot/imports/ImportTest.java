package com.example.springboot.imports;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Auther: 59315
 * @Date: 2020/5/3 23:38
 * @Description: 
 */
public class ImportTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for(String name : beanDefinitionNames){
            System.out.println("bean名称："+name);
        }

    }


}
