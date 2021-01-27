package com.example.springboot.annotation;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @version : 1.0
 * @Description: TODO
 * @Auther: ywb
 * @Date: 2020/6/8 9:18
 */
@Configuration
@ComponentScan("com.example.springboot.annotation")
@Import(MyImportBeanDefinitionRegister.class)
public class AppConfig2 {

//    @Bean(value = "cat")
//    public Cat cat() {
//        return new Cat();
//    }
//
//
//    @Bean(value = "tiger", initMethod = "init", destroyMethod = "destroyInit")
//    public Tiger tiger(){
//        return new Tiger();
//    }
}
