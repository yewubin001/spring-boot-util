package com.example.springboot.design.行为型模式.listener.guava;

import com.google.common.eventbus.AsyncEventBus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;


/**
 * @Auther: yewub
 * @Date: 2019/2/15 11:49
 * @Description: EventBus是Guava的事件处理机制，是设计模式中的观察者模式（生产/消费者编程模型）的优雅实现。
 * 对于事件监听和发布订阅模式，EventBus是一个非常优雅和简单解决方案，我们不用创建复杂的类和接口层次结构。
 */
@Configuration
public class BeanConfig {
    @Bean
    public AsyncEventBus asyncEventBus() {
        return new AsyncEventBus(Executors.newFixedThreadPool(100));
    }

}
