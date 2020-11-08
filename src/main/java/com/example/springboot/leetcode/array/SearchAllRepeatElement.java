package com.example.springboot.leetcode.array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @version : 1.0
 * @Description:
 * @Auther: ywb
 * @Date: 2020/8/18 16:08
 */
public class SearchAllRepeatElement {

    public static void main(String[] args) {
        char[] array = {'d', 'a', 'b', 'c', 'c', 'b', 'b', 'c'};
        System.out.println(repeat(array));
    }

    /**
     * 如果数组包含多个重复值，如何搜索所有重复值？
     */
    public static Set repeat(char[] array) {
        Map<Object, Integer> map = new HashMap<>();
        Set set = new HashSet();
        for (int i = array.length - 1; i > -1; i--) {
            char key = array[i];
            if (map.containsKey(key)) {
                Integer value = map.get(key);
                map.put(key, value++);
                set.add(key);
            } else {
                map.put(key, 1);
            }
        }
        return set;
    }

}
