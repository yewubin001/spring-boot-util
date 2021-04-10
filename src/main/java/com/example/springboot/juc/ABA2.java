package com.example.springboot.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @description: 模拟CAS中的ABA问题
 * @author: yewubin
 * @date: 2021/4/10 15:56
 * @version: v1.0
 */
public class ABA2 {

    public static AtomicStampedReference<Integer> a = new AtomicStampedReference<>(new Integer("1"), 1);

    public static void main(String[] args) {

        Thread mianThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("操作线程" + Thread.currentThread().getName() + ", 初始值： " + a.getReference());
                try {
                    Integer expectRefrence = a.getReference();
                    int expectStamp = a.getStamp();
                    int newRefrence = expectRefrence + 1;
                    int newStemp = expectStamp + 1;
                    TimeUnit.MILLISECONDS.sleep(1000);
                    boolean isSuccess = a.weakCompareAndSet(expectRefrence, newRefrence, expectStamp, newStemp);
                    System.out.println("操作线程：" + Thread.currentThread().getName() + ", CAS操作：" + isSuccess);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "主线程");


        Thread other = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("操作线程" + Thread.currentThread().getName() + ", 初始值： " + a.getReference());
                try {
                    TimeUnit.MILLISECONDS.sleep(20);
                    a.set(a.getReference() + 1, a.getStamp() + 1);
                    System.out.println("操作线程" + Thread.currentThread().getName() + "【increment】,值=" + a.getReference());
                    a.set(a.getReference() - 1, a.getStamp() + 1);
                    System.out.println("操作线程" + Thread.currentThread().getName() + "【increment】,值=" + a.getReference());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }, "干扰线程");

        mianThread.start();
        other.start();
    }


}
