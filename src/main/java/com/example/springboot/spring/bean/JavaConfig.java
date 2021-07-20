package com.example.springboot.spring.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 管理bean的生命周期：
 * 1、InitializingBean 和 DisposableBean回调接口。
 * 2、自定义init() 和 destroy() 方法。
 * 3、@PostConstruct 和 @PreDestroy注解。
 *
 * 初始化和销毁顺序都是：3 > 1 > 2
 */
@Configuration
public class JavaConfig {

    @Bean(initMethod = "initMethod", destroyMethod = "destroyMethod")
    public MyInitializingBean getInitializingBean() {
        return new MyInitializingBean();
    }

}
