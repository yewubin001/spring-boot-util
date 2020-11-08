package com.example.springboot.leetcode.array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @version : 1.0
 * @Description: 给定一个数组，如何搜索重复元素？
 * @Auther: ywb
 * @Date: 2020/9/9 11:49
 */
public class SearchRepeatElement {


    public static void main(String[] args) {
        char[] array = {'d', 'a', 'b', 'c', 'c', 'b', 'b', 'c'};
        // 1
        System.out.println(firstRepeat(array));

        // 2
        SearchRepeatElement searchRepeatElement = new SearchRepeatElement();
        CountIndex countIndex = searchRepeatElement.firstNoRepeat(array);
        System.out.println(countIndex);
    }


    /**
     * 查找数组中第一个重复元素（如果是查找第一个重复元素，则倒序遍历最后一个）
     */
    public static char firstRepeat(char[] array) {
        Map<Object, Integer> map = new HashMap<>();
        for (int i = array.length - 1; i > -1; i--) {
            char key = array[i];
            if (map.containsKey(key)) {
                return key;
            } else {
                map.put(key, 1);
            }
        }
        return 0;
    }


    /**
     * 第一个不重复算法分析（次数为1，索引最小）
     */
    public CountIndex firstNoRepeat(char[] array) {
        Map<Object, CountIndex> map = new HashMap<>();
        for (int i=0; i<array.length; i++)
        if (!map.containsKey(array[i])) {
            CountIndex countIndex = new CountIndex();
            countIndex.setCount(1);
            countIndex.setIndex(i);
            map.put(array[i], countIndex);
        } else {
            CountIndex countIndex = map.get(array[i]);
            countIndex.setCount(countIndex.getCount()+1);
            map.put(array[i], countIndex);
        }

        for(int i=0; i<array.length; i++) {
            if(map.get(array[i]).getCount()==1) {
                return map.get(array[i]);
            }
        }
        return null;
    }


    class CountIndex {
        /**
         * 出现次数
         */
        private int count;
        /**
         * 索引
         */
        private int index;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        @Override
        public String toString() {
            return "CountIndex{" +
                    "count=" + count +
                    ", index=" + index +
                    '}';
        }
    }

}
