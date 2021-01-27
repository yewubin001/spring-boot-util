package com.example.springboot;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {

    public static final char[] s1 = "ABCDEFGH".toCharArray();
    public static final char[] s2 = "12345678".toCharArray();

    public static void main(String[] args) {

        Lock lock = new ReentrantLock();
        Condition c1 = lock.newCondition();
        Condition c2 = lock.newCondition();


        new Thread(() -> {
            try{
                lock.lock();
                for(char c : s1){
                    c2.signal();
                    c1.await();
                    System.out.print(c);
                }
                c2.signal();
            }catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }

        },"t1").start();

        new Thread(() -> {
            try{
                lock.lock();
                for(char c : s2){
                    c1.signal();
                    c2.await();
                    System.out.print(c);
                }
                c1.signal();
            }catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }

        },"t2").start();

    }
}
