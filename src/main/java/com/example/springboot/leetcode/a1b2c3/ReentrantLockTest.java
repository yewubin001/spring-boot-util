package com.example.springboot.leetcode.a1b2c3;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther: 59315
 * @Date: 2020/5/18 22:54
 * @Description: 面试题：两个线程输出 A1B2C3D4E5F6G7
 */
public class ReentrantLockTest {

    public static void main(String[] args) {
        char[] ai = "1234567".toCharArray();
        char[] ac = "ABCDEFG".toCharArray();

        Lock lock = new ReentrantLock();
        Condition c1 = lock.newCondition();
        Condition c2 = lock.newCondition();

        new Thread(() -> {
            try {
                lock.lock();
                for (char num : ai) {
                    System.out.print(num);
                    c2.signal();
                    c1.await();
                }
                c2.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }, "t1").start();

        new Thread(() -> {
            try {
                lock.lock();
                for (char num : ac) {
                    System.out.print(num);
                    c1.signal();
                    c2.await();
                }
                c1.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t2").start();

    }

}
