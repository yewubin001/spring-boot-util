package com.example.springboot.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @Auther: yewub
 * @Date: 2019/2/14 11:50
 * @Description: @Configuration的作用类似于配置一个spring-bean.xml中的<beans></beans>标签的作用，主要用于Bean的注入,放置在类上。
 * 使用该注解注入Bean后，获取IOC容器的方式不再使用ClassPathXmlApplicationContext或者文件系统路径来获取对应的IOC容器，而是使用
 * AnnotationConfigApplicationContext(AnnotationConfiguration.class)的方式来获取IOC容器(AnnotationConfiguration为自定义的配置类)。
 *
 * springboot 不用加@ComponentScan注解， SpringBoot会自动帮你把其他包都扫描了。
 */
@Configuration
@ComponentScan("com.example.springboot.configuration")
@Import(MyImportBeanDefinitionRegistrar.class)
public class AppConfig {
    public AppConfig() {
        System.out.println("初始化...");
    }

    @Bean(initMethod = "")
    public Fox getFox() {
        Fox fox = new Fox();
        fox.setName("jack");
        return fox;
    }

}
