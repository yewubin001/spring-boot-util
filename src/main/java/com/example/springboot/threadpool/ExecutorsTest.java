package com.example.springboot.threadpool;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Auther: yewub
 * @Date: 2019/6/14 10:46
 * @Description:
 */
public class ExecutorsTest {

    /**
     * newCachedThreadPool
     * 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
     */
    @Test
    public void newCachedThreadPool() {
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + ":cachedThreadPool");
                }
            });
            System.out.println("************* a" + i + " *************");
        }
    }

    /**
     * newFixedThreadPool
     * 创建固定大小的线程池。每次提交一个任务就创建一个线程，直到线程达到线程池的最大大小。
     */
    @Test
    public void newFixedThreadPool() {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        executor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ":newFixedThreadPool");
            }
        });
    }


    /**
     * 创建一个单线程的Executor，如果该线程因为异常而结束就新建一条线程来继续执行后续的任务
     */
    @Test
    public void newSingleThreadExecutor() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ":newSingleThreadExecutor");
            }
        });
    }

    /**
     * 创建一个可延迟执行或定期执行的线程池
     */
    public static void main(String[] args){
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(5);
        executor.scheduleAtFixedRate(
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(Thread.currentThread().getName() + ":newScheduledThreadPool");
                    }
                }, 2, 3, TimeUnit.SECONDS
        );
    }

    /**
     * 任务分两类：一类是实现了Runnable接口的类，一类是实现了Callable接口的类。
     * 两者都可以被ExecutorService执行，但是Runnable任务没有返回值，而Callable任务有返回值。
     * 并且Callable的call()方法只能通过ExecutorService的submit(Callable task) 方法来执行，
     * 并且返回一个 Future，是表示任务等待完成的 Future
     */
    @Test
    public void callable() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Future<String>> resultList = new ArrayList<>();

        //创建10个任务并执行
        for (int i = 0; i < 10; i++) {
            final int a = i;
            //使用ExecutorService执行Callable类型的任务，并将结果保存在future变量中
            Future<String> future = executorService.submit(new Callable<String>() {
                @Override
                public String call() {
                    System.out.println("call()方法被自动调用！！！" + Thread.currentThread().getName());
                    //该返回结果将被Future的get方法得到
                    return "call()方法被自动调用，任务返回的结果是：" + a + "    " + Thread.currentThread().getName();
                }
            });
            //将任务执行结果存储到List中
            resultList.add(future);
        }

        //遍历任务的结果
        for (Future<String> fs : resultList) {
            try {
                while (!fs.isDone()) {
                    ;//Future返回如果没有完成，则一直循环等待，直到Future返回完成
                }
                //打印各个线程（任务）执行的结果
                System.out.println(fs.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } finally {
                //启动一次顺序关闭，执行以前提交的任务，但不接受新任务
                executorService.shutdown();
            }
        }
    }
}
