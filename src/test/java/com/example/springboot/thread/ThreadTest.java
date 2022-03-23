package com.example.springboot.thread;

import lombok.SneakyThrows;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * @Author yewubin
 * 多线程测试
 * Date 2022-03-23 16:05
 */
public class ThreadTest {
    /**
     * 第一种：继承Thread类  主线程必须等待线程t执行完之后再执行
     */
    @Test
    public void thread1() throws InterruptedException {
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName() + " 开始...");
                    TimeUnit.MILLISECONDS.sleep(2000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " 结束...");
            }
        };
        thread.start();
        thread.join();
    }

    /**
     * 第二种：实现Runnable接口  主线程必须等待线程t执行完之后再执行
     */
    @Test
    public void thread2() throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " 开始...");
                TimeUnit.MILLISECONDS.sleep(5000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " 结束...");
        });
        thread.start();
        thread.join();
    }

    /**
     * 第三种：实现Callable接口并通过FutureTask包装
     */
    @Test
    public void thread3() throws ExecutionException, InterruptedException {
        FutureTask<Object> futureTask = new FutureTask<>(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                System.out.println(Thread.currentThread().getName() + " 开始执行...");
                TimeUnit.MILLISECONDS.sleep(5000);
                System.out.println(Thread.currentThread().getName() + " 开始结束...");
                return "success";
            }
        });
        Thread thread = new Thread(futureTask);
        thread.start();
        System.out.println(futureTask.get());
    }

    /**
     * 第四种：通过线程池submit()方法 执行Callable线程任务，有返回值
     */
    @Test
    public void thread4() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Future<Object> future = executorService.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                System.out.println("executor submit callable...start");
                TimeUnit.MILLISECONDS.sleep(5000);
                System.out.println("executor submit callable...end");
                return "success";
            }
        });
        while (!future.isDone()) {

        }
        System.out.println(future.get());
    }

    /**
     * 第五种：通过线程池submit()方法 执行Runnable线程任务，无返回值，future.get()为null
     */
    @Test
    public void thread5() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Future<?> future = executorService.submit(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                System.out.println("executor submit runnable...start");
                TimeUnit.MILLISECONDS.sleep(5000);
                System.out.println("executor submit runnable...end");
            }
        });
        while (!future.isDone()) {
        }
        System.out.println("executor submit runnable... over");
    }

    /**
     * 第五种：通过线程池submit()方法 执行Runnable线程任务，无返回值，future.get()为null
     */
    @Test
    public void thread6() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.execute(new Runnable() {
            @Override
            public void run() {

            }
        });
    }
}