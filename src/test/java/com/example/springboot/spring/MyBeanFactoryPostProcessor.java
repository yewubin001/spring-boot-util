package com.example.springboot.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.stereotype.Component;

/**
 * @Auther: 59315
 * @Date: 2020/6/8 22:03
 * @Description:
 */
@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        // 承载bean的属性  class.newInstance()
        GenericBeanDefinition beanDefinition = (GenericBeanDefinition)beanFactory.getBeanDefinition("user");
        // user ---> cat
//        beanDefinition.setBeanClassName("com.example.springboot.spring.Cat");
        // 构造器贪婪模式
         beanDefinition.setAutowireMode(3);

        beanDefinition.getPropertyValues().add("name", "fox");
        beanDefinition.getPropertyValues().add("age", 20);

    }
}
