package com.example.springboot.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @description: SpringContextUtil 工具类
 * @author: yewubin
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {

    /**
     * 上下文
     */
    private static ApplicationContext applicationContext;

    /**
     * 获取上下文
     *
     * @return
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 设置上下文
     *
     * @param applicationContext
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
    }

    /**
     * 获取属性配置
     *
     * @param key
     * @return
     */
    public static String getProperty(String key) {
        if (applicationContext == null) {
            throw new RuntimeException("applicationContext is null");
        }
        if (applicationContext.getEnvironment() == null) {
            throw new RuntimeException("environment is null");
        }
        return applicationContext.getEnvironment().getProperty(key);
    }

}
