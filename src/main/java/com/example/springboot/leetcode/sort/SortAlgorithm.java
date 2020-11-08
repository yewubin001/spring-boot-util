package com.example.springboot.leetcode.sort;

import java.util.Arrays;

/**
 * @version : 1.0
 * @Description: 排序算法
 * @Auther: ywb
 * @Date: 2020/9/18 16:57
 */
public class SortAlgorithm {

    public static void main(String[] args) {
        int[] array = new int[]{1, 4, 20, 6, 0, 9, 3, 7, 50, 33};
        System.out.println("------------冒泡排序------------");
        bubbleSort(array);
        System.out.println("------------选择排序------------");
        selectSort(array);
        System.out.println("------------插入排序-----------");
        insertionSort(array);
    }


    /**
     * 冒泡排序，时间复杂度：O(n2)
     * 比较相邻的元素。如果第一个比第二个大，就交换它们两个；
     * 对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对，这样在最后的元素应该会是最大的数；
     *
     * @param arr
     */
    public static void bubbleSort(int[] arr) {
        if (arr.length == 0) {
            return;
        }
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            System.out.println("第" + i + "次排序結果" + Arrays.toString(arr));
        }
        System.out.println("冒泡排序结果：" + Arrays.toString(arr));
    }


    /**
     * 选择排序，时间复杂度：O(n2)
     * 首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，然后，再从剩余未排序元素中继续寻找最小（大）元素，
     * 然后放到已排序序列的末尾。以此类推，直到所有元素均排序完毕。
     */
    public static void selectSort(int[] array) {
        if (array.length == 0) {
            return;
        }
        for (int i = 0; i < array.length; i++) {
            int minIndex = i;
            for (int j = i; j < array.length; j++) {
                if (array[minIndex] > array[j]) {
                    minIndex = j;
                }
            }
            int temp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = temp;
            System.out.println("第" + (i + 1) + "次排序結果" + Arrays.toString(array));
        }
        System.out.println("选择排序：" + Arrays.toString(array));
    }


    /**
     * 插入排序，时间复杂度：O(n2)
     * 从第一个元素开始，该元素可以认为已经被排序；
     * 取出下一个元素，在已经排序的元素序列中从后向前扫描；
     * 如果该元素（已排序）大于新元素，将该元素移到下一位置；
     * 重复步骤3，直到找到已排序的元素小于或者等于新元素的位置；
     * 将新元素插入到该位置后；
     *
     * @param array
     */
    public static void insertionSort(int[] array) {
        if (array.length == 0) {
            return;
        }
        int current;
        for (int i = 0; i < array.length - 1; i++) {
            current = array[i + 1];
            int preIndex = i;
            while (preIndex >= 0 && current < array[preIndex]) {
                array[preIndex + 1] = array[preIndex];
                preIndex--;
            }
            array[preIndex + 1] = current;
        }
        System.out.println("插入排序：" + Arrays.toString(array));
    }



}
// https://www.cnblogs.com/guoyaohua/p/8600214.html