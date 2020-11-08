package com.example.springboot.leetcode.a1b2c3;

import java.util.concurrent.CountDownLatch;

/**
 * @Auther: 59315
 * @Date: 2020/5/18 22:54
 * @Description: 面试题：两个线程输出 A1B2C3D4E5F6G7
 */
public class SyncWaitNotifyTest {

    public static void main(String[] args) {
        final Object o = new Object(); // 通过拿到对象的锁

        final CountDownLatch countDownLatch = new CountDownLatch(1);  //控制线程执行顺序

        char[] ai = "1234567".toCharArray();
        char[] ac = "ABCDEFG".toCharArray();

        new Thread(() -> {
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (o) {
                for (char num : ai) {
                    System.out.print(num);
                    try {
                        o.notify();
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify(); //必须，否则无法停止程序
            }
        }, "t1").start();

        new Thread(() -> {
            synchronized (o) {
                for (char zimu : ac) {
                    System.out.print(zimu);
                    countDownLatch.countDown();
                    try {
                        o.notify();
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify(); //必须，否则无法停止程序
            }
        }, "t2").start();

    }

}
