package com.example.springboot.leetcode;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Auther: 59315
 * @Date: 2020/6/1 23:05
 * @Description:
 */
public class ReadWriterLockTest {


    private static int number = 0;

    private static ReadWriteLock readWriterLock = new ReentrantReadWriteLock();

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    readWriterLock.readLock().lock();
                    System.out.println(Thread.currentThread().getName() + ":" + number);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    readWriterLock.readLock().unlock();
                }
            }, "read" + i).start();
        }

        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    readWriterLock.writeLock().lock();
                    number = (int)(Math.random()*101);
                    System.out.println(Thread.currentThread().getName() + ":" + number);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    readWriterLock.writeLock().unlock();
                }
            }, "write" + i).start();
        }

    }
}
