package com.example.springboot.juc;

import org.junit.Test;

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
 *
 * 倒计时器示例:火箭发射
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(10);
        ExecutorService exec = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            exec.submit(() -> {
                // 模拟检查任务
                try {
                    Thread.sleep(1000);
                    System.out.println("check complete");
                    //计数减一
                    latch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        // 等待检查
        latch.await();

        // 发射火箭
        System.out.println("Fire!");
        // 关闭线程池
        exec.shutdown();
    }

    @Test
    public void test() throws InterruptedException {
        CountDownLatch cdAnswer = new CountDownLatch(5);
        CountDownLatch cdOrder = new CountDownLatch(1);
        ExecutorService exec = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            exec.submit(() -> {
                // 模拟检查任务
                try {
                    System.out.println("选手"+Thread.currentThread().getName()+"正在等待裁判发布口令");
                    cdOrder.await();
                    System.out.println("选手"+Thread.currentThread().getName()+"已接受判发布口令");
                    Thread.sleep((long) (Math.random()*1000));
                    System.out.println("选手"+Thread.currentThread().getName()+"到达终点");
                    //计数减一
                    cdAnswer.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        Thread.sleep((long) (Math.random()*1000));
        cdOrder.countDown();
        System.out.println(Thread.currentThread().getName()+"裁判已发送口令");
        // 等待检查
        cdAnswer.await();

        // 发射火箭
        System.out.println("所有选手到达终点!");
        // 关闭线程池
        exec.shutdown();
    }
}