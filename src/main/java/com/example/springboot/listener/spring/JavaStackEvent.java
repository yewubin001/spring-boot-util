package com.example.springboot.listener.spring;

import org.springframework.context.ApplicationEvent;

/**
 *事件(event) 的创建
 */
public class JavaStackEvent extends ApplicationEvent {
    public JavaStackEvent(Object source) {
        super(source);
    }
}
