package com.example.springboot.juc;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @Auther: 59315
 * @Date: 2020/4/25 23:18
 * @Description:
 */
public class ForkJoinTest {
    /**
     * 最小处理任务单元
     */
    private static final int MIN_FORK = 2;


    public static void main(String[] args) throws Exception {

        ForkJoinPool pool = new ForkJoinPool();

        ForkJoinTest.ForkJoinSumTask sumTask = new ForkJoinTest().new ForkJoinSumTask(1, 10000);
        //提交任务 和 ExecutorService 是一样的，只不过返回值是一个ForkJoinTask
        ForkJoinTask<Integer> joinTask = pool.submit(sumTask);
        System.out.println(joinTask.get());
    }


    /**
     * 简单的累加任务
     */
    public class ForkJoinSumTask extends RecursiveTask<Integer> {

        int sum;

        int start;

        int end;

        public ForkJoinSumTask(int start, int end) {

            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            //小于最小任务单元 直接处理,不需要新增任务并行处理
            if (start - end < MIN_FORK) {
                for (int i = start; i <= end; i++) {
                    sum += i;
                }

                return sum;
            }
            //取中位数
            int midNum = (start + end) / 2;
            ForkJoinSumTask left = new ForkJoinSumTask(start, midNum);
            ForkJoinSumTask right = new ForkJoinSumTask(midNum + 1, end);

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //不能这样fork  会导致本线程资源浪费
            //  left.fork();
            //  right.join();

            invokeAll(left, right);
            sum = left.join() + right.join();
            return sum;
        }
    }
}