package com.example.springboot.leetcode.array;

import java.util.*;

/**
 * @version : 1.0
 * @Description:
 * @Auther: ywb
 * @Date: 2020/8/18 16:08
 */
public class DeleteRepeatElement {

    public static void main(String[] args) {
        char[] array = {'d', 'a', 'b', 'c', 'c', 'b', 'b', 'c'};
        System.out.println(deleteRepeat(array));

        System.out.println(Arrays.toString(fourClear(array)));
    }


    /**
     * 给定一个数组，如何用 Java 删除重复元素？如何在不使用库的情况下移除数组中的重复元素？
     * 利用Set集合的不可重复性进行元素过滤
     * 要想保持原数组的顺序就使用有顺序、不重复特点的链表的哈希集合
     *
     * @param chars
     */
    public static Set deleteRepeat(char[] chars) {
        //Set<Character> set = new LinkedHashSet<>();
        Set<Character> set = new HashSet<>();
        for (char c : chars) {
            set.add(c);
        }
        return set;
    }

    /**
     * 不使用库的情况下移除数组中的重复元素？
     *
     * @param arr
     * @return
     */
    public static Object[] fourClear(char[] arr) {
        int t = 0;
        //临时数组
        Object[] xinArr = new Object[arr.length];

        for (int i = 0; i < arr.length; i++) {
            //声明标记，是否重复
            boolean isRepeat = true;
            for (int j = i + 1; j < arr.length; j++) {
                //如果有重复元素，将标记置为false
                if (arr[i] == arr[j]) {
                    isRepeat = false;
                    break;
                }
            }
            //标记为true表示没有重复元素
            if (isRepeat) {
                xinArr[t] = arr[i];
                t++;
            }
        }
        //去重后数组
        Object[] newArr = new Object[t];
        System.arraycopy(xinArr, 0, newArr, 0, t);
        return newArr;
    }
}
