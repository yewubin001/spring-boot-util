package com.example.springboot.design.行为型模式.listener.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author yewubin
 * <p>
 * Date 2021-05-13 23:50
 */
@Slf4j
@Configuration
public class ObserverConfiguration {

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext context) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) {
                System.out.println("发布事件，什么是观察者模式？");
                context.publishEvent(new JavaStackEvent("什么是观察者模式？"));
            }
        };
    }

    @Bean
    public ReaderListener readerListener1() {
        return new ReaderListener("小明");
    }
    @Bean
    public ReaderListener readerListener2() {
        return new ReaderListener("小张");
    }
    @Bean
    public ReaderListener readerListener3() {
        return new ReaderListener("小爱");
    }
}
