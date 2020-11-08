package com.example.springboot.leetcode.array;

import java.util.Arrays;

/**
 * @version : 1.0
 * @Description: 给定一个乱序数组，如何搜索最大和最小元素？
 * @Auther: ywb
 * @Date: 2020/8/18 16:08
 */
public class SearchMaxAndMinElement {

    private static int[] array = new int[100];

    public static void main(String[] args) {
        int[] arr = new int[]{1, 4, -2, 6, 0, 9, 14, 12, 50, 33};
        getMaxAndMin(arr);

        bubbleSort(arr);
    }


    /**
     * method1：逐个比较
     *
     * @param arr
     */
    public static void getMaxAndMin(int[] arr) {
        int max = arr[0];
        int min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        System.out.println("min:" + min + ",max=" + max);
    }


    /**
     * method2：先排序，再取值
     *
     * @param arr
     */
    public static void bubbleSort(int[] arr) {
        if(arr.length <= 1)  return;

        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
        System.out.println("min:" + arr[0] + ",max:" + arr[arr.length - 1]);
    }


}
