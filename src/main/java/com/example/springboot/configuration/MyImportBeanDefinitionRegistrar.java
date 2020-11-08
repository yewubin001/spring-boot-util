package com.example.springboot.configuration;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @Auther: 59315
 * @Date: 2020/5/3 23:45
 * @Description: 
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        // new一个RootBeanDefinition
        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(Cat.class);
        // 注册一个名字叫rectangle的bean
        registry.registerBeanDefinition("cat", rootBeanDefinition);
    }
}
