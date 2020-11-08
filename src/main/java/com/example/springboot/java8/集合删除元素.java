package com.example.springboot.java8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: 59315
 * @Date: 2019/11/10 22:26
 * @Description: ArrayList的底层结构是数组类型，数组这种数据结构的特点是删除其中某个元素时，
 * 后面的所有元素索引都会前移，此时for循环的指针却会下移，因此会略过下一个元素，解决方案是删除时将指针回调一次
 * 这个问题只会出现在使用for进行遍历的时候，使用迭代器来操作时，不会出现这种问题：
 */
public class 集合删除元素 {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("w");
        list.add("li");
        list.add("li");
        list.add("z");


        System.out.println("list original is " + list.toString());

//        Iterator<String> iterator = list.iterator();
//        while (iterator.hasNext()){
//            String value = iterator.next();
//            if("li".equals(value)){
//                iterator.remove();
//            }
//        }

        for (int i = 0; i < list.size(); i++) {
            if ("li".equals(list.get(i))) {
                list.remove(i);
                i--;  //删除时将指针回调一次
            }
        }

        System.out.println("after list is " + list.toString());

    }

    /**
     * 其次集合中的元素动态删除时，增强for无法动态删除，而普通for可以动态删除。
     * 在增强for动态删除时会报一个 java.util.ConcurrentModificationException的错误
     * (不是删除每个元素都会抛异常，AbstractList中的modCount与ArrayList中的expectedModCount不相等)，
     * 而普通for却没有这个问题，但是每删除一个元素时，集合的size会发生变化，删除会有遗漏，所以建议使用迭代器Iterator对集合进行动态删除操作
     */
    @Test
    public void test() {
        List<String> list = new ArrayList<>();

        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");

        for (String str : list) {
            if ("3".equals(str)) {
                list.remove(str);
            }
        }
//
//        for(int i=0; i<list.size(); i++){
//            if("2".equals(list.get(i))){
//                list.remove(i);
//                //i--;
//            }
//        }
        System.out.println(list.toString());
    }
}
