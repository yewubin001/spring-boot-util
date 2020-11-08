package com.example.springboot.java8.concurrent;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author Magfin
 * @version : 1.0
 * @Description: TODO
 * @Date: 2020/5/11 9:50
 */

@Slf4j
public class SemaphoreDemo {

    private static final Logger log = LoggerFactory.getLogger(SemaphoreDemo.class);

    /**
     * 排队总人数（请求总数）
     */
    public static int clientTotal = 10;

    /**
     * 可同时受理业务的窗口数量（同时并发执行的线程数）
     */
    public static int threadTotal = 2;


    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            final int count = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire(1);
                    resolve(count);
                } catch (Exception e) {
                    log.error("exception", e);
                } finally {
                    semaphore.release(1);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
    }

    private static void resolve(int i) throws InterruptedException {
        log.info("服务号{}，受理业务中。。。", i);
        Thread.sleep(2000);
        log.info("服务号{}，受理业务完成", i);
    }
}
