package com.example.springboot.controller;

import com.example.springboot.aop.UserService;
import com.example.springboot.delayqueue.queue.DelayQueueService;
import com.example.springboot.delayqueue.queue.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author yewub
 */
@RestController
public class CustController {

    @Autowired
    private UserService userService;

    @Autowired
    private DelayQueueService delayQueueService;

    @RequestMapping("/list-student")
    public String listStudent() {
        return userService.listStudent("yewubin");
    }


    @RequestMapping("/user-info")
    public String userInfo() {
        userService.getUserInfo("ywb", 12);
        return "success";
    }

    @RequestMapping("/delay-queue")
    public String delayQueue(){
        DelayQueue<Order> delayQueue = delayQueueService.getDelayQueue();
        Order order = new Order("order", 15, TimeUnit.SECONDS);
        delayQueue.put(order);
        return "success";
    }
}