package com.example.springboot.java8.concurrent;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @version : 1.0
 * @Description: TODO
 * @Auther: ywb
 * @Date: 2020/1/7 16:02
 */
public class Test01ConcurrentLinkedQueue {
    public static void main(String[] args) throws InterruptedException {
        //吃饭人数
        int peopleNum = 10000;
        //饭桌数量
        int tableNum = 10;

        ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();
        //计数器
        CountDownLatch count = new CountDownLatch(tableNum);

        //将吃饭人数放入队列（吃饭的人进行排队）
        for (int i = 1; i <= peopleNum; i++) {
            queue.offer("消费者_" + i);
        }
        //执行10个线程从队列取出元素（10个桌子开始供饭）
        System.out.println("-----------------------------------开饭了-----------------------------------");
        long start = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(tableNum);
        for (int i = 0; i < tableNum; i++) {
            executorService.submit(new Dinner("00" + (i + 1), queue, count));
        }
        //计数器等待，直到队列为空（所有人吃完）
        count.await();
        long time = System.currentTimeMillis() - start;
        System.out.println("-----------------------------------所有人已经吃完-----------------------------------");
        System.out.println("共耗时：" + time);
        //停止线程池
        executorService.shutdown();
    }

    private static class Dinner implements Runnable {
        private String name;
        private ConcurrentLinkedQueue<String> queue;
        private CountDownLatch count;

        public Dinner(String name, ConcurrentLinkedQueue<String> queue, CountDownLatch count) {
            this.name = name;
            this.queue = queue;
            this.count = count;
        }

        @Override
        public void run() {
            //while (queue.size() > 0){
            while (!queue.isEmpty()) {
                //从队列取出一个元素 排队的人少一个
                System.out.println(Thread.currentThread().getName()+"【" + queue.poll() + "】----已吃完...， 饭桌编号：" + name);
            }
            count.countDown();//计数器-1
        }
    }
}