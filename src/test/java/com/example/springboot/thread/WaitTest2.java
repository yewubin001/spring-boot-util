package com.example.springboot.thread;

/**
 * @Author yewubin
 * <p>
 * Date 2022-04-18 23:46
 */
public class WaitTest2 {
    public static void main(String[] args) {
        ThreadA t1 = new ThreadA("t1");
        synchronized (t1) {
            try {
                System.out.println(Thread.currentThread().getName() + " start t1");
                t1.start();// 启动“线程t1”
                System.out.println("挂起主线程");// 主线程等待t1通过notify()唤醒。
                System.out.println("线程t1的状态是:" + t1.isAlive());
                t1.wait();  //  不是使t1线程等待，而是让拥有t1这个对象的主线程等待
                System.out.println("挂起主线程后面的输出");
            } catch (InterruptedException e) {
            }
        }
    }
}

class ThreadA extends Thread {
    public ThreadA(String name) {
        super(name);
    }
    @Override
    public void run() {
        synchronized (this) {
            try {
                System.out.println("ThreadA start ....");
                //  使当前线阻塞 1 s确保在主线程wait()之前t1没有执行完并退出
                Thread.sleep(1000);
            } catch (Exception e) {
            }
        }
    }
}