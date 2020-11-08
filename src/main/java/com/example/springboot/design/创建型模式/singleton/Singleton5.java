package com.example.springboot.design.创建型模式.singleton;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @Auther: 59315
 * @Date: 2020/10/20 23:23
 * @Description: CAS 乐观锁 实现单例
 * 优点：不需要使用传统的锁机制来保证线程安全
 * 缺点：如果忙等待一直执行不成功(一直在死循环中),会对CPU造成较大的执行开销
 */
public class Singleton5 {

    private static AtomicReference<Singleton5> reference = new AtomicReference();

    private Singleton5() {
    }

    public static Singleton5 getInstance() {
        for (; ; ) {
            Singleton5 singleton5 = reference.get();
            if (singleton5 != null) {
                return singleton5;
            }
            singleton5 = new Singleton5();
            if (reference.compareAndSet(null, singleton5)) {
                return singleton5;
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> System.out.println(getInstance().hashCode())).start();
        }
    }
}
