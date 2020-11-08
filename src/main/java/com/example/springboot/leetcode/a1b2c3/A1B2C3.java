package com.example.springboot.leetcode.a1b2c3;

/**
 * @Auther: 59315
 * @Date: 2020/5/18 22:54
 * @Description: 面试题：两个线程输出 1A2B3C4D5E6F7G
 */
public class A1B2C3 {

    enum ReadyToRun{T1,T2}

    static volatile ReadyToRun r = ReadyToRun.T1;

    public static void main(String[] args) {
        char[] ai = "1234567".toCharArray();
        char[] ac = "ABCDEFG".toCharArray();

        new Thread(() -> {
            for (char num : ai) {
                while(r != ReadyToRun.T1){}
                System.out.print(num);
                r = ReadyToRun.T2;
            }
        }, "t1").start();

        new Thread(() -> {
            for (char zimu : ac) {
                while (r != ReadyToRun.T2){}
                System.out.print(zimu);
                r = ReadyToRun.T1;
            }
        }, "t2").start();
    }

}
