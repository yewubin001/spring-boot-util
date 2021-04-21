package com.example.springboot.leetcode;

/**
 * @Author yewubin
 * 求区间内所有数据之和
 * Date 2021-04-21 23:30
 */
public class SectionSum {
    public static void main(String[] args) {
        System.out.println("循环求和：" + sum_loop(1, 100));
        System.out.println("递归求和：" + sum_recursion(1, 100));
    }
    public static int sum_loop(int start, int end) {
        int sum = 0;
        for (int i = start; i <= end; i++) {
            sum += i;
        }
        return sum;
    }
    public static int sum_recursion(int start, int end) {
        if (start == end) {
            return start;
        }
        return start + sum_recursion(start + 1, end);
    }
}
