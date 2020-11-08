package com.example.springboot.juc;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: yewub
 * @Date: 2019/4/25 14:11
 * @Description:
 */
public class ForkJoinPoolTest extends RecursiveAction {

    /**
     * 每个“小任务”只最多只打印50个数
     */
    private static final int THRESHOLD = 50;
    private int start;
    private int end;

    /**
     * 打印从start到end的任务
     *
     * @param start
     * @param end
     */
    public ForkJoinPoolTest(int start, int end) {
        this.start = start;
        this.end = end;
    }


    @Override
    protected void compute() {
        // 当end与start之间的差小于THRESHOLD时，开始打印
        if (end - start < THRESHOLD) {
            for (int i = start; i < end; i++) {
                System.out.println(Thread.currentThread().getName() + "的i值：" + i);
            }
        } else {
            // 如果当end与start之间的差大于THRESHOLD时，即要打印的数超过50个
            // 将大任务分解成两个小任务。
            int middle = (start + end) / 2;
            ForkJoinPoolTest left = new ForkJoinPoolTest(start, middle);
            ForkJoinPoolTest right = new ForkJoinPoolTest(middle, end);
            // 并行执行两个“小任务”
            invokeAll(left, right);
        }
    }

    public static void main(String[] args) throws Exception {
        ForkJoinPool pool = new ForkJoinPool();
        // 提交可分解的PrintTask任务
        pool.submit(new ForkJoinPoolTest(0, 300));
        pool.awaitTermination(2, TimeUnit.SECONDS);
        // 关闭线程池
        pool.shutdown();
    }
}
