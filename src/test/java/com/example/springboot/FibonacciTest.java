package com.example.springboot;

/**
 * @Author yewubin
 * 斐波那契数列
 * Date 2021-04-22 23:19
 */
public class FibonacciTest {

    //  0,1,1,2,3,5,8,13
    public static void main(String[] args) {
        System.out.println(f(10));
    }


    public static int f(int index) {
        if(index==1) return 0;
        if (index==2) return 1;
        return f(index-1) + f(index-2);
    }


}
