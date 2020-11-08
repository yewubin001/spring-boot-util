package com.example.springboot.leetcode.a1b2c3;

import java.util.concurrent.locks.LockSupport;

/**
 * @Auther: 59315
 * @Date: 2020/5/18 22:54
 * @Description: 面试题：两个线程输出 1A2B3C4D5E6F7G
 */
public class LockSupportTest {

    static Thread t1 = null, t2 = null;

    public static void main(String[] args) {
        char[] ai = "1234567".toCharArray();
        char[] ac = "ABCDEFG".toCharArray();

        t1 = new Thread(() -> {
            for (char num : ai) {
                System.out.print(num);
                LockSupport.unpark(t2); //不分先后顺序，如果先执行unpark(),
                LockSupport.park();
            }
        }, "t1");

        t2 = new Thread(() -> {
            for (char zimu : ac) {
                LockSupport.park(); //阻塞
                System.out.print(zimu);
                LockSupport.unpark(t1);
            }
        }, "t2");

        t1.start();
        t2.start();
    }

}
