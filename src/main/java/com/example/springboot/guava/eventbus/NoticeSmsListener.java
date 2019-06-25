package com.example.springboot.guava.eventbus;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.Subscribe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @Auther: yewub
 * @Date: 2019/2/15 11:57
 * @Description:
 */
@Component
public class NoticeSmsListener {
    @Autowired
    private AsyncEventBus asyncEventBus;

    /**
     * 注册这个监听器
     */
    @PostConstruct
    public void register() {
        asyncEventBus.register(this);
    }
    @PreDestroy
    public void destroy() {
        asyncEventBus.unregister(this);
    }

    @Subscribe
    public void sendSms(NoticeSmsEvent smsEvent) {
        //这里写需要异步执行的逻辑
        System.out.println("发送短信。。。"+smsEvent.getContent());
    }
}
