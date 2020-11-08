package com.example.springboot.leetcode;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther: 59315
 * @Date: 2020/6/1 22:20
 * @Description: 
 */
public class InterruptLock {

    public static void main(String[] args) {

        ReentrantLock lock = new ReentrantLock(true);
        Thread t = new Thread(() -> {
            try{
                lock.lock();
                System.out.println("tttttt");
                Thread.sleep(5000);
                System.out.println("1111111");
            } catch (Exception e){

            } finally {
                lock.unlock();
            }
        }, "t");

        t.start();
        t.interrupt();

    }

}
