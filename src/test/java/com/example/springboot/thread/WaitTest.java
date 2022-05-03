package com.example.springboot.thread;

import java.util.concurrent.TimeUnit;

/**
 * @Author yewubin
 * <p>
 * Date 2022-04-19 10:13
 */
public class WaitTest {
    public static String a = "";
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " is running...");
            }
        });
        thread.start();
        thread.join();
        System.out.println("线程运行结束了...");
    }
}
class ThreadB extends Thread {
    public ThreadB(String name) {
        super(name);
    }
    @Override
    public void run() {
        synchronized (WaitTest.a) {
            System.out.println("ThreadB start ...");
            try {
                Thread.sleep(1000); //  使当前线阻塞 1 s，确保主程序的 wait()执行之后再执行 notify()
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}