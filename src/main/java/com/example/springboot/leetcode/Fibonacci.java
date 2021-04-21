package com.example.springboot.leetcode;

/**
 * 1、介绍：斐波那契数列：0、1、1、2、3、5、8、13、21、34
 * 从第三位开始，每一个数都是前两个数之和
 *
 * 2、要求：输出一定数量的斐波那契数列
 */
public class Fibonacci {
    /**
     * @param args
     */
    public static void main(String[] args) {
        // 循环实现
        fibonacci_loop(7);
        // 递归实现
        fibonacci_recursion(7);
    }

    public static void fibonacci_recursion(int num){
        fibonacci_recursion(0,1, 1, 7);
    }

    private static void fibonacci_recursion(int one, int two, int index, int num) {
        // 出口条件，终止递归调用
        if(index > num){
            return ;
        }
        if(index == 1) {
            System.out.println(one);
            System.out.println(two);
        }
        System.out.println(one+two);
        fibonacci_recursion(two, one+two, ++index, num);
    }
    public static void fibonacci_loop(int num) {
        if (num < 1) {
            return;
        }
        int n = 0;int a = 0;int b = 1;int c;
        do {
            n++;
            if (n == 1) {
                System.out.println(a);
                System.out.println(b);
            }
            c = a + b;
            System.out.println(c);
            a = b;
            b = c;
        } while (n < num);
    }
}
