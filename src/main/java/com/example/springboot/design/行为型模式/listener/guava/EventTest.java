package com.example.springboot.design.行为型模式.listener.guava;

import com.google.common.eventbus.AsyncEventBus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @description:
 * @author: yewubin
 * @date: 2021/5/13 9:55
 * @version: v1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EventTest {
    @Autowired
    private AsyncEventBus asyncEventBus;
    @Test
    public void test() {
        System.out.println("-----");
        SmsEvent event = new SmsEvent(2L, "15221146305", "老子今天不上班");
        asyncEventBus.post(event);
    }

}
