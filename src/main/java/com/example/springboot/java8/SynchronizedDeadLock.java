package com.example.springboot.java8;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @version : 1.0
 * @Description: TODO
 * @Auther: ywb
 * @Date: 2019/12/14 14:38
 */
public class SynchronizedDeadLock {

    private static final SynchronizedDeadLock A = new SynchronizedDeadLock();
    private static final SynchronizedDeadLock B = new SynchronizedDeadLock();

    public void test1(){
        // 获取临界区A
        synchronized (A) {
            System.out.println(Thread.currentThread().getName()+" get lockA success");
            // 模拟耗时操作
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 获取临界区B
            synchronized (B) {
                System.out.println(Thread.currentThread().getName()+" get lockB success");
            }
        }
    }
    public void test2(){
        // 获取临界区B
        synchronized (B) {
            System.out.println(Thread.currentThread().getName()+" get lockB success");
            // 模拟耗时操作
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 获取临界区A
            synchronized (A) {
                System.out.println(Thread.currentThread().getName()+" get locA success");
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(() -> {
            A.test1();
        });
        executorService.execute(() -> {
            B.test2();
        });
    }

}
//public class SynchronizedDeadLock {
//
//    private static final Object lockA = new Object();
//    private static final Object lockB = new Object();
//
//    /**
//     * ThreadA先获取lockA,在获取lockB
//     */
//    private static class ThreadA extends java.lang.Thread {
//
//        @Override
//        public void run() {
//            // 获取临界区A
//            synchronized (lockA) {
//                System.out.println(Thread.currentThread().getName()+"get lockA success");
//                // 模拟耗时操作
//                try {
//                    Thread.sleep(500);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                // 获取临界区B
//                synchronized (lockB) {
//                    System.out.println(Thread.currentThread().getName()+" get lockB success");
//                }
//            }
//        }
//    }
//
//    /**
//     * ThreadB先获取lockB,在获取lockA
//     */
//    private static class ThreadB extends java.lang.Thread {
//
//        @Override
//        public void run() {
//            // 获取临界区A
//            synchronized (lockB) {
//                System.out.println(Thread.currentThread().getName()+"get lockB success");
//                // 模拟耗时操作
//                try {
//                    Thread.sleep(500);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                // 获取临界区B
//                synchronized (lockA) {
//                    System.out.println(Thread.currentThread().getName()+"get lockA success");
//                }
//            }
//        }
//    }
//
//    public static void main(String[] args) {
//        ThreadA a = new ThreadA();
//        ThreadB b = new ThreadB();
//        a.start();
//        b.start();
//    }
//}