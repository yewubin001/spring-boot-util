package com.example.springboot.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: 59315
 * @Date: 2021/4/5 12:31
 * @Description:
 */
public class CASDemo2 {

    // 总访问量
    private static volatile int count = 0;

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        CASDemo2.count = count;
    }

    /**
     * 模拟客户请求
     * <p>
     * Q：锁直接加在方法上，耗时太长
     * A：request方法加锁相当于串行执行
     * <p>
     * Q：如何解决耗时太长的问题
     * A：
     * 1. 获取锁
     * 2. 获取count的最新值  记作LV
     * 3. 判断LV是否等于A，如果相等则将B的值赋给count，并返回true，否则返回false
     * 4. 释放锁
     */
    public static void request() throws InterruptedException {
        //  模拟耗时5毫秒
        TimeUnit.MILLISECONDS.sleep(5);
        /**
         * Q:问题出在哪里?
         * A:count++操作 实际上是3步操作完成的
         *   1. 获取count的值  记作：A= count
         *   2. 将A值加1 得到B=A+1
         *   3. 将B值赋给count
         *  如果A、B两个线程同时执行count++，他们同时执行到第一步，得到的count的值一样
         *  3步操作之后，count只加1，导致count的结果不正确。
         *
         * Q:怎么解决这个问题？
         * A：对count++操作的时候，让多个线程排队执行，多个线程同时到达request()方法时，只能允许一个线程操作，其他线程等待
         *
         * Q：怎么实现排队效果？
         * A：Java中的synchronized关键字heReentrantLock都可以实现对资源加锁，保证并发正确性
         */
        int expectvalue;
        while (!compareAndSwap((expectvalue=getCount()), expectvalue + 1)) { }
    }


    /**
     * 同步方法， 线程安全
     *
     * @param expectVal
     * @param newVal
     * @return
     */
    public static synchronized boolean compareAndSwap(int expectVal, int newVal) {
        if (expectVal == getCount()) {
            setCount(newVal);
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        int threadSize = 100;
        CountDownLatch countDownLatch = new CountDownLatch(threadSize);

        for (int i = 0; i < threadSize; i++) {
            Thread thread = new Thread(() -> {
                try {
                    // 模拟用户行为，每个用户访问10次网站
                    for (int j = 0; j < 10; j++) {
                        request();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            }, "thread" + i);
            thread.start();
        }

        // 保证100个线程执行之后，再执行后面的代码
        countDownLatch.await();
        long endTime = System.currentTimeMillis();

        System.out.println(Thread.currentThread().getName() + "耗时：" + (endTime - startTime) + ", count= " + count);
    }


}
