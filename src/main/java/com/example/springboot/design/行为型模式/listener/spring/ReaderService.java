package com.example.springboot.design.行为型模式.listener.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * @Author yewubin
 * 
 * Date 2021-05-13 23:19
 */
@Service
public class ReaderService {
    @Autowired
    private ApplicationContext applicationContext;

    public void sendEvent() {
        // 发送 JavaStackEvent 事件 , 发送完成后，监听该事件的监听器就会监听到该事件
        applicationContext.publishEvent(new JavaStackEvent("orderID"));
    }

}
