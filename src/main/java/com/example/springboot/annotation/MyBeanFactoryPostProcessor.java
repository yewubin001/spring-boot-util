package com.example.springboot.annotation;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * @author Magfin
 * @version : 1.0
 * @Description: TODO
 * @Auther: ywb
 * @Date: 2020/6/8 14:41
 */
//@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        //BeanDefinition beanDefinition = beanFactory.getBeanDefinition("user");
        // 可以修改class信息
        //beanDefinition.setBeanClassName("com.example.springboot.annotation.Cat");

        GenericBeanDefinition beanDefinition = (GenericBeanDefinition)beanFactory.getBeanDefinition("user");
        // 构造器贪婪模式
        beanDefinition.setAutowireMode(3);
        beanDefinition.getPropertyValues().add("name", "fox");
        beanDefinition.getPropertyValues().add("age", 30);
    }
}
