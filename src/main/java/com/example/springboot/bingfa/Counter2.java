package com.example.springboot.bingfa;
/**
 * @Auther: 59315
 * @Date: 2020/4/18 14:57
 * @Description: 
 */

public class Counter2 {
    private  int count = 0;

    private MySynchronized lock = new MySynchronized();

    public void inc() {
        lock.lock();
        count++;
        lock.unlock();
    }

    public static void main(String[] args) {

        //同时启动1000个线程，去进行i++计算，看看实际结果
        final Counter2 c = new Counter2();

        for (int i = 0; i < 1000; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    c.inc();
                }
            }).start();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //这里每次运行的值都有可能不同,可能为1000
        System.out.println("运行结果:Counter.count=" + c.count);
    }
}
