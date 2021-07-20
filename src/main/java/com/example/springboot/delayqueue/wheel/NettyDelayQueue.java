package com.example.springboot.delayqueue.wheel;

import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.Timer;
import io.netty.util.TimerTask;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Author yewubin
 * 
 * Date 2021-07-17 23:17
 *
 * 因为任务超时后不能马上被worker线程执行，需要等到worker线程移到相应卡槽位置时才会执行，因此说执行时间不精确。
 */
public class NettyDelayQueue {

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        /**
         *  @param tickDuration 每格时间间隔 默认 100
         *  @param unit 时间单位 默认 毫秒
         *  @param ticksPerWheel 轮子size（一圈多少格）                                                                                         默认 512 如果不是2的N次方，则去大于该参数的第一个2的N次方
         */
        final Timer timer = new HashedWheelTimer(Executors.defaultThreadFactory(), 5, TimeUnit.SECONDS, 2);

        // 定时任务
        TimerTask task1 = new TimerTask() {
            @Override
            public void run(Timeout timeout) throws Exception {
                System.out.println("order1  5s 后执行 "+ System.currentTimeMillis());

                timer.newTimeout(this, 5, TimeUnit.SECONDS);//结束时候再次注册
            }
        };
        timer.newTimeout(task1, 5, TimeUnit.SECONDS);

        TimerTask task2 = new TimerTask() {
            @Override
            public void run(Timeout timeout) throws Exception {
                System.out.println("order2  10s 后执行");
                timer.newTimeout(this, 10, TimeUnit.SECONDS);//结束时候再注册
            }
        };
        timer.newTimeout(task2, 10, TimeUnit.SECONDS);

        // 延迟任务
        timer.newTimeout(new TimerTask() {
            @Override
            public void run(Timeout timeout) throws Exception {
                System.out.println("order3  15s 后执行一次");
            }
        }, 15, TimeUnit.SECONDS);

    }
}
