package com.example.springboot.leetcode.array;

import java.util.Arrays;
import java.util.BitSet;

/**
 * @version : 1.0
 * @Description: 在一个元素为 1 到 100 的整数数组中，如何搜索缺失元素？
 * @Auther: ywb
 * @Date: 2020/8/18 16:08
 */
public class SearchLostElement {

    private static int[] array = new int[100];

    public static void main(String[] args) {
        // 在一个元素为 1 到 100 的整数数组中，如何搜索缺失元素？
        init();
        System.out.println(getMissingNumber(array, array.length));


        System.out.println("-----------------------");

        printMissingNumber(new int[]{1, 2, 3, 5, 6, 8, 10}, 10);
    }


    /**
     * 只能搜索一个缺失元素
     */
    private static int getMissingNumber(int[] numbers, int totalCount) {
        int expectedSum = totalCount * (totalCount + 1) / 2;
        int actualSum = 0;
        for (int i : numbers) {
            actualSum += i;
        }
        return expectedSum - actualSum;
    }


    /**
     * 输出缺失的元素
     *
     * @param numbers
     * @param count
     */
    private static void printMissingNumber(int[] numbers, int count) {
        int missingCount = count - numbers.length;
        BitSet bitSet = new BitSet(count);
        for (int number : numbers) {
            bitSet.set(number - 1);  // 将指定索引处的位设置为 true
        }
        System.out.printf("Missing numbers in integer array %s, with total number %d is %n", Arrays.toString(numbers), count);
        int lastMissingIndex = 0;
        System.out.println("返回此BitSet设置为true的个数：" + bitSet.cardinality());
        // 方法1
        for (int i = 0; i < missingCount; i++) {
            lastMissingIndex = bitSet.nextClearBit(lastMissingIndex);  //返回在指定的起始索引之后设置为 false的第一个位的索引
            System.out.println(++lastMissingIndex);
        }

        // 方法2
        System.out.println("缺失元素");
        for (int j = 0; j < count; j++) {
            if (!bitSet.get(j)) {
                System.out.print(j+1);
            }
        }

    }

    /**
     * init
     */
    private static void init() {
        for (int i = 0; i < array.length; i++) {
            if (i == 49) {
                continue;
            }
            array[i] = i + 1;
        }
    }
}
