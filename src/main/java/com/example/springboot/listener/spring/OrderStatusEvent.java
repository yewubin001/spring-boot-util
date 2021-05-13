package com.example.springboot.listener.spring;

import org.springframework.context.ApplicationEvent;

/**
 *事件(event) 的创建
 */
public class OrderStatusEvent extends ApplicationEvent {
    public OrderStatusEvent(Object source) {
        super(source);
    }
}
