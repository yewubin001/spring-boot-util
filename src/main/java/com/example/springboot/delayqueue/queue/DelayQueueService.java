package com.example.springboot.delayqueue.queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Author yewubin
 * <p>
 * Date 2021-07-18 22:59
 */
@Component
public class DelayQueueService {

    private DelayQueue<Order> delayQueue;

    @Autowired
    private AsyncMethod asyncMethod;

    public DelayQueue<Order> getDelayQueue() {
        return delayQueue;
    }

    @PostConstruct
    public void test() throws InterruptedException {
        delayQueue = new DelayQueue<>();
        Order Order1 = new Order("Order1", 5, TimeUnit.SECONDS);
        Order Order2 = new Order("Order2", 10, TimeUnit.SECONDS);
        Order Order3 = new Order("Order3", 15, TimeUnit.SECONDS);
        delayQueue.put(Order1);
        delayQueue.put(Order2);
        delayQueue.put(Order3);
        System.out.println("订单延迟队列开始时间:" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        asyncMethod.process();
    }


}
