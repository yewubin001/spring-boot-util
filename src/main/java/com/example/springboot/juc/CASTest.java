package com.example.springboot.juc;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Auther: 59315
 * @Date: 2020/5/30 18:32
 * @Description:
 */

public class CASTest {

    //static int count = 0;

    static AtomicInteger atomicReference = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 2; i++) {
            // 匿名内部类
            new Thread(() -> {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int j = 0; j < 100; j++) {
                    // count++ 结果小于200 不是原子操作。
                    // 原子性
                    atomicReference.incrementAndGet();
                }
            }).start();
        }

        Thread.sleep(2000);

        System.out.println(atomicReference);

        LinkedList linkedList = new LinkedList(Arrays.asList("111","123","345"));
        System.out.println(linkedList.get(1));

    }
}
