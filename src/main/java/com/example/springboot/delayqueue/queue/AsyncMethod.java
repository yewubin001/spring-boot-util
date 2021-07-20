package com.example.springboot.delayqueue.queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.DelayQueue;

/**
 * @Author yewubin
 * 
 * Date 2021-07-20 23:14
 */
@Component
public class AsyncMethod {

    @Autowired
    DelayQueueService delayQueueService;

    @Async
    public void process() throws InterruptedException {
        DelayQueue<Order> delayQueue = delayQueueService.getDelayQueue();
        while (true) {
            Order task = delayQueue.take();
            if (task != null) {
                System.out.format("订单:{%s}被取消, 取消时间:{%s}\n", task.name, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            }
        }
    }
}
