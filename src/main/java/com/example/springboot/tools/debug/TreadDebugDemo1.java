package com.example.springboot.tools.debug;

import java.math.BigInteger;

/**
 * @Auther: 59315
 * @Date: 2021/4/2 23:42
 * @Description:
 */
public class TreadDebugDemo1 {
    // 开启两个线程
    // frames -> 查看线程调用栈
    // 线程控制面板 -> suspend有两个选项：All（所有线程） 和 Thread（当前线程，可以切换线程查看每个线程执行的副本）
    // 可以使用Evaluate -> Thread.currentThread.getName() 查看当前执行线程名
    public static void main(String[] args) {
        // 第一个线程计算100！
        AddThread thread1 = new AddThread(100);
        // 第二个线程计算10000！
        AddThread thread2 = new AddThread(10000);

        thread1.setName("thread1");
        thread2.setName("thread2");

        thread1.start();
        thread2.start();

        try {
            thread1.join(); // 线程join， 以使主线程在线程1和线程2都返回结果之前不会进一步执行
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        BigInteger add = thread1.getResult().add(thread2.getResult());
        System.out.println("将两个线程计算结果相加等于：" + add);
    }
    private static class AddThread extends Thread {
        private BigInteger result = BigInteger.ONE;
        private long num;

        public AddThread(long num) {
            this.num = num;
        }
        public BigInteger getResult() {
            return result;
        }
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " 开始计算：" + num);
            add(num);
            System.out.println(Thread.currentThread().getName() + "执行完成");
        }
        public void add(long num) {
            BigInteger f = new BigInteger("1");
            for (int i = 1; i < num; i++) {
                f = f.add(BigInteger.valueOf(i));
            }
            result = f;
        }
    }
}




