package com.example.springboot.java8.concurrent;

/**
 * @version : 1.0
 * @Description: TODO
 * @Auther: ywb
 * @Date: 2020/1/19 18:25
 */

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest implements Runnable {

    private static AtomicInteger flag = new AtomicInteger(10);

    public static void main(String[] args) {
        AtomicIntegerTest ast = new AtomicIntegerTest();
        flag.incrementAndGet();
        flag.incrementAndGet();
        System.out.println(flag);


//        Thread thread1 = new Thread(ast);
//        Thread thread = new Thread(ast);
//        thread1.start();
//        thread.start();
    }
    @Override
    public void run() {
        System.out.println("thread:"+Thread.currentThread().getName()+";flag:"+flag.get());
        if (flag.compareAndSet(10,11)){
            System.out.println(Thread.currentThread().getName()+""+flag.get());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            flag.set(10);
        }else{
            System.out.println("重试机制thread:"+Thread.currentThread().getName()+";flag:"+flag.get());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            run();
        }

    }
}
