package com.example.springboot.annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @version : 1.0
 * @Description: TODO
 * @Auther: ywb
 * @Date: 2020/6/8 9:27
 */
public class AppTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig2.class);

//        DefaultListableBeanFactory beanFactory = context.getDefaultListableBeanFactory();
//
//        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(Fox.class);
//
//        beanFactory.registerBeanDefinition("fox", rootBeanDefinition);
//
//        Fox bean = context.getBean(Fox.class);
//        System.out.println(bean);


//        Cat cat = new Cat();
//        beanFactory.registerSingleton("cat", cat);

        System.out.println(context.getBean("myFactoryBean"));
        System.out.println(context.getBean("&myFactoryBean"));


        ApplicationContext applicationContext = MyAware.getApplicationContext();
        System.out.println("............."+applicationContext.getEnvironment());
        context.close();

    }

}
