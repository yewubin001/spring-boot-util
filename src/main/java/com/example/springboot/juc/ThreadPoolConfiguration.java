package com.example.springboot.juc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @description: guava单应用异步事件
 * @author: Douma | Hao xijun
 * @date: 2018/11/20 18:06
 */
@Configuration
public class ThreadPoolConfiguration {

    @Bean
    public ThreadPoolExecutor threadPoolExecutor() {
        return new ThreadPoolExecutor(
            50,
            100,
            60L,
            TimeUnit.SECONDS,
                new LinkedBlockingQueue<>());
    }

    @Bean
    public ScheduledThreadPoolExecutor scheduledThreadPoolExecutor(){
        return  new ScheduledThreadPoolExecutor(50);
    }

}
