package com.example.springboot.listener.spring;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 监听器的创建
 * 实现ApplicationListener 接口即可， ApplicationListener 中的泛型指定为对应的事件
 * 切记要把该监听器注入到Spring中哦 !!!
 */
@Component
public class OrderStatusListener implements ApplicationListener<OrderStatusEvent> {
    /**
     * Handle an application event.
     * @param event the event to respond to
     */
    @Override
    public void onApplicationEvent(OrderStatusEvent event) {
        // 监听到 对应的事件
        System.out.println("监听器处理逻辑..."+event.getSource());
    }
}
