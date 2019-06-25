package com.example.springboot.threadpool;

import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Auther: 59315
 * @Date: 2019/6/17 22:12
 * @Description:
 */
public class ThreadTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        runnable();
        thread();
        callable();
    }

    /**
     * 实现Runnable接口
     */
    public static void runnable() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " is running...");
            }
        });
        thread.start();
    }

    /**
     * 继承Thread类
     */
    public static void thread() {
        new Thread() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " is running...");
            }
        }.start();
    }

    /**
     * 实现Callable接口并通过FutureTask包装
     */
    public static void callable() throws ExecutionException, InterruptedException {
        FutureTask<String> future = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() {
                System.out.println(Thread.currentThread().getName() + " is running...");
                return this.toString();
            }
        });
        Thread thread = new Thread(future);
        thread.start();
        System.out.println(future.get());
    }

    //线程池
}
