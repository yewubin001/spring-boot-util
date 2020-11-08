package com.example.springboot.bingfa;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.LockSupport;

/**
 * @Auther: 59315
 * @Date: 2020/4/19 11:43
 * @Description: 自己实现synchronized
 */

public class MySynchronized {

    /**
     * 当前线程状态，记录加锁的次数
     */
    private volatile int state = 0;

    /**
     * 当前持有锁的线程
     */
    private Thread lockHolder;

    /**
     * 线程阻塞队列
     */
    private ConcurrentLinkedQueue<Thread> queue = new ConcurrentLinkedQueue<>();

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Thread getLockHolder() {
        return lockHolder;
    }

    public void setLockHolder(Thread lockHolder) {
        this.lockHolder = lockHolder;
    }

    /**
     * 尝试获取锁
     *
     * @return
     */
    private boolean tryAquire() {
        Thread t = Thread.currentThread();
        int state = getState();
        if (state == 0) {
            //公平锁
            if ((queue.size() == 0 || t.equals(queue.peek())) && compareAndSwapState(0, 1)) {
                setLockHolder(t);
                return true;
            }
        }
        return false;
    }

    /**
     * 加锁
     */
    public void lock() {
        // 1、获取锁  -- CAS
        if (tryAquire()) {
            return;
        }
        Thread current = Thread.currentThread();
        queue.add(current);
        // 2、停留在当前线程
        for (; ; ) {
            if (queue.peek() == current && tryAquire()) {
                System.out.println("hold lock Thread-name :" + current);
                queue.poll();
                return;
            }
            //阻塞线程
            LockSupport.park(current);
        }

        // 3、锁被释放后再次获取锁

    }

    /**
     * 释放锁
     */
    public void unlock() {
        Thread current = Thread.currentThread();
        if (current != lockHolder) {
            throw new RuntimeException("不是当前持有锁的线程，不可以释放锁");
        }
        int state = getState(); //1
        if (compareAndSwapState(state, 0)) {
            System.out.println(String.format("Thread-name: %s,释放锁成功", current.getName()));
            setLockHolder(null);
            Thread head = queue.peek();
            if (head != null) {
                LockSupport.unpark(head);
            }
        }

    }


    /**
     * 原子操作
     *
     * @param oldValue 线程工作内存当中的值
     * @param newValue 要替换的新值
     * @return
     */
    public final boolean compareAndSwapState(int oldValue, int newValue) {
        return unsafe.compareAndSwapInt(this, valueOffset, oldValue, newValue);
    }

    private static final Unsafe unsafe;

    private static final long valueOffset;

    static {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            unsafe = (Unsafe) theUnsafe.get(null);
            valueOffset = unsafe.objectFieldOffset
                    (MySynchronized.class.getDeclaredField("state"));
        } catch (Exception ex) {
            throw new Error(ex);
        }
    }

}
