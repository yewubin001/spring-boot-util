package com.example.springboot.design.行为型模式.listener.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author yewubin
 * 
 * Date 2021-05-13 23:22
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ReaderTest {

    @Autowired
    private ReaderService orderStatusService;

    @Test
    public void test() {
        orderStatusService.sendEvent();
    }

}
