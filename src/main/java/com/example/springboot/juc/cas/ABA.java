package com.example.springboot.juc.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description: 模拟CAS中的ABA问题
 * @author: yewubin
 * @date: 2021/4/10 15:56
 * @version: v1.0
 */
public class ABA {

    public static AtomicInteger a = new AtomicInteger(1);

    public static void main(String[] args) {

        Thread mainThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("操作线程" + Thread.currentThread().getName() + ", 初始值： " + a.get());
                try {
                    int expectNum = a.get();
                    int newNum = expectNum+1;
                    TimeUnit.MILLISECONDS.sleep(1000);  // 让主线程休眠一秒，让出cpu
                    boolean isSuccess = a.compareAndSet(expectNum, newNum);
                    System.out.println("操作线程：" + Thread.currentThread().getName() + ", CAS操作：" + isSuccess);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "主线程");


        Thread other = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("操作线程" + Thread.currentThread().getName() + ", 初始值： " + a.get());
                try {
                    TimeUnit.MILLISECONDS.sleep(20); // 确保main线程优先执行
                    a.incrementAndGet(); // a=a+1 =2
                    System.out.println("操作线程" + Thread.currentThread().getName() + "【increment】,值=" + a.get());
                    a.decrementAndGet(); // a=a-1 =1
                    System.out.println("操作线程" + Thread.currentThread().getName() + "【decrement】,值=" + a.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }, "干扰线程");

        mainThread.start();
        other.start();
    }



}
