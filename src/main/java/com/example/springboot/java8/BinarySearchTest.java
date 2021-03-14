package com.example.springboot.java8;

/**
 * @Auther: 59315
 * @Date: 2021/3/14 12:18
 * @Description:
 */
public class BinarySearchTest {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        System.out.println("循环查找：" + binarySearch(arr, 15));
        System.out.println("递归查找：" + binarySearch2(arr, 0, arr.length - 1, 15));
    }

    /**
     * 循环实现二分查找算法
     *
     * @param array 有序数组
     * @param data  需要查找的数
     * @return 返回-1 没有查到数据
     */
    public static int binarySearch(int[] array, int data) {
        int low = 0;
        int height = array.length - 1;
        int n = 0;
        while (low <= height) {
            System.out.println("第" + (++n) + "次");
            int middle = (low + height) >>> 1; // 防止溢出
            if (data == array[middle]) {
                return middle;
            } else if (data > array[middle]) {
                low = middle + 1;
            } else {
                height = middle - 1;
            }
        }
        // 若没有，则返回-1
        return -1;
    }

    /**
     * 二分查找递归实现
     *
     * @param array  有序数组
     * @param low    数组低位下标
     * @param height 数组高位下标
     * @param data   要查找的数据
     * @return
     */
    public static int binarySearch2(int[] array, int low, int height, int data) {
        if (low <= height) {
            // int middle = (height - low) /2 + low;
            int middle = (low + height) >>> 1; // 防止溢出
            if (data == array[middle]) {
                return middle;
            } else {
                if (data > array[middle]) {
                    return binarySearch2(array, middle + 1, height, data);
                } else {
                    return binarySearch2(array, low, middle - 1, data);
                }
            }
        } else {
            return -1;
        }
    }

}
