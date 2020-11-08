package com.example.springboot.leetcode.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: 59315
 * @Date: 2020/10/9 23:02
 * @Description: 给定一个数值，如何搜索整数数组中加和为该数值的成对元素？
 */

public class SearchSumEqualPairElement {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 4, 5, 8, 3};
        int[] ints = twoSum(arr, 13);
        System.out.println(Arrays.toString(ints));
    }


    /**
     * 给定一个数值，如何搜索整数数组中加和为该数值的成对元素？
     *
     * @param arr
     * @param sum
     * @return
     */
    public static int[] twoSum(int[] arr, int sum) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int a = sum - arr[i];
            if (map.containsKey(a)) {
                return new int[]{a, arr[i]};
            }
            map.put(arr[i], i);
        }
        return null;
    }

}
