package com.example.springboot.configuration;

import com.example.springboot.async.VisiableThreadPoolTaskExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Auther: 59315
 * @Date: 2022/3/22
 * @Description: 自定义线程池配置类
 * 方式一：不实现AsyncConfigurer接口，使用@Async("")需要指定beanId才行
 * 方式二：现AsyncConfigurer重写他的两个方法，这样在使用默认的线程池的时候就会使用自己重写的
 *
 */
@Configuration
@EnableAsync
public class ExecutorConfig implements AsyncConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(ExecutorConfig.class);

    @Autowired
    private ApplicationProperties properties;

//    @Bean("taskExecutor")
//    public ThreadPoolTaskExecutor taskExecutor() {
//        logger.info("start taskExecutor");
//        //ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//        VisiableThreadPoolTaskExecutor executor = new VisiableThreadPoolTaskExecutor();
//        //配置核心线程数
//        executor.setCorePoolSize(properties.getTaskThreadPool().getCorePoolSize());
//        //配置最大线程数
//        executor.setMaxPoolSize(properties.getTaskThreadPool().getMaxPoolSize());
//        //配置队列大小
//        executor.setQueueCapacity(properties.getTaskThreadPool().getKeepAliveSeconds());
//        //配置线程池中的线程的名称前缀
//        executor.setThreadNamePrefix("async-service-");
//        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
//        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
//        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
//        //执行初始化
//        executor.initialize();
//        return executor;
//    }

    @Bean("taskExecutor")
    @Override
    public Executor getAsyncExecutor() {
        logger.info("start taskExecutor");
        //ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        VisiableThreadPoolTaskExecutor executor = new VisiableThreadPoolTaskExecutor();
        //配置核心线程数
        executor.setCorePoolSize(properties.getTaskThreadPool().getCorePoolSize());
        //配置最大线程数
        executor.setMaxPoolSize(properties.getTaskThreadPool().getMaxPoolSize());
        //配置队列大小
        executor.setQueueCapacity(properties.getTaskThreadPool().getKeepAliveSeconds());
        //配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix("async-service-");
        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //执行初始化
        executor.initialize();
        return new ExceptionHandlingAsyncTaskExecutor(executor);
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new AsyncUncaughtExceptionHandler() {
            @Override
            public void handleUncaughtException(Throwable arg0, Method arg1, Object... arg2) {
                logger.error("==========================" + arg0.getMessage() + "=======================", arg0);
                logger.error("exception method:" + arg1.getName());
            }
        };
    }
}
