package com.example.springboot.leetcode;
/**
 * @version : 1.0
 * @Description: 求数组中两个数相加等于给定的值
 * @Auther: ywb
 * @Date: 2020/6/15 10:40
 */
public class TowSum {


    public static void main(String[] args) {

        int[] arrs = {2, 1, 7, 5, 6, 9};

        int[] ints = twoSum(arrs, 11);

        System.out.println(Arrays.toString(ints));
    }


    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            int value = target - nums[i];
            // 如果 map 存在此差值，则返回
            if (map.containsKey(value)) {
                return new int[]{i, map.get(value)};
            }
            // 将该数组的值存入 map
            map.put(nums[i], i);
        }
        return null; 
    }

}
