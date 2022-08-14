package com.example.springboot.spring;

import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;
import java.text.NumberFormat;

/**
 * @Auther: yewub
 * @Date: 2019/2/14 13:35
 * @Description:
 */
@SpringBootTest
public class AppTest {

    @Test
    public void test1() {
        // ioc容器
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        //上面的代码等同于：
        //AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
        //context.register(AppConfig.class);
        Fox fox = (Fox) context.getBean("fox");
        System.out.println(fox);

        // BeanDefinition bean定义 承载bean的属性 init-method scope lazy
        // BeanDefinitionRegistry 注册器
        // registerBeanDefinition(String beanName, BeanDefinition beanDefinition)
        // BeanDefinitionMap key(beanName)  value:BeanDefinition(Fox.class)
        // beanFactoryPostProcessor

        // 实现了BeanDefinitionRegistry   BeanFactory
        DefaultListableBeanFactory beanFactory = context.getDefaultListableBeanFactory();
        // 注册了Cat.class 的beanDefinition
        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(Cat.class);
        beanFactory.registerBeanDefinition("cat", rootBeanDefinition);

        // singletonObjects 缓存单例bean   beanName ----- singletonObjects
        Cat cat = new Cat();
        beanFactory.registerSingleton("cat", cat);


        System.out.println(context.getBean("user"));
    }

    public static void main(String[] args) {
        NumberFormat percentInstance = NumberFormat.getPercentInstance();
        //最大整数位数
        percentInstance.setMaximumIntegerDigits(3);
        //最小小数位数
        percentInstance.setMinimumFractionDigits(4);
        System.out.println(percentInstance.format(new BigDecimal("0.1212")));
    }

}
