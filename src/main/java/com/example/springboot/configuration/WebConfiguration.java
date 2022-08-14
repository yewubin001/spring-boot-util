package com.example.springboot.configuration;

import com.example.springboot.idempotent.AutoIdempotentInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author yewubin
 * 拦截器配置类
 * Date 2022-08-14 22:26
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Autowired
    private AutoIdempotentInterceptor autoIdempotentInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册自己的拦截器并设置拦截的请求路径
        registry.addInterceptor(autoIdempotentInterceptor).addPathPatterns("/**");
    }
}
