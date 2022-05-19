package com.example.springboot.spring.annotation.condition;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: yewub
 * @Date: 2019/4/17 11:06
 * @Description:配置类
 */
@Configuration
public class ConditionConfig {

    /**
     * 通过@Conditional注解，符合Windows条件则实例化WindowsListService
     *
     * @return
     */
    @Bean
    @Conditional(WindowsCondition.class)
    public ListService windowsListService() {
        return new WindowsListService();
    }

    /**
     * 通过@Conditional注解，符合Linux条件则实例化LinuxListService
     *
     * @return
     */
    @Bean
    @Conditional(LinuxCondition.class)
    public ListService linuxListService() {
        return new LinuxListService();
    }

}
