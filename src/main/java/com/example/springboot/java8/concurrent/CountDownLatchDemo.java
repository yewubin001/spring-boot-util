package com.example.springboot.java8.concurrent;

import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auther: yewub
 *
 * <p>
 * CountDownLatch是一个非常实用的多线程控制工具类。常用的就下面几个方法：
 * CountDownLatch(int count) //实例化一个倒计数器，count指定计数个数
 * countDown() // 计数减一
 * await() //等待，当计数减到0时，所有线程并行执行
 * <p>
 * 倒计时器示例:火箭发射
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(100);
        ExecutorService exec = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {
            exec.submit(() -> {
                // 模拟检查任务
                try {
                        Thread.sleep(1);
                        System.out.println(LocalDateTime.now() +Thread.currentThread().getName() + "：check complete");
                        //计数减一
                        countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        // 等待检查
        countDownLatch.await();
        // 发射火箭
        System.out.println("Fire!");
        // 关闭线程池
        exec.shutdown();
    }
}