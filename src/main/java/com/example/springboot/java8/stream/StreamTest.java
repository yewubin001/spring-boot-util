package com.example.springboot.java8.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Auther: yewub
 * @Date: 2019/5/6 15:18
 * @Description:
 */
public class StreamTest {
    @Test
    public void stream1() {
        List<String> strings = Arrays.asList("Hollis", "HollisChuang", "hollis", "Hello", "HelloWorld", "Hollis");
        Stream<String> stream = strings.stream();
    }

    @Test
    public void stream2() {
        Stream<String> stream2 = Stream.of("Hollis", "HollisChuang", "hollis", "Hello", "HelloWorld", "Hollis");

    }

    /**
     * filter 方法用于通过设置的条件过滤出元素。以下代码片段使用 filter 方法过滤掉空字符串
     */
    @Test
    public void filter() {
        List<String> strings = Arrays.asList("Hollis", "", "HollisChuang", "H", "hollis");
        strings.stream().filter(s -> !s.isEmpty()).forEach(System.out::println);
    }

    /**
     * map 方法用于映射每个元素到对应的结果，以下代码片段使用 map 输出了元素对应的平方数
     */
    @Test
    public void map() {
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        numbers.stream().map(i -> i * i).forEach(System.out::println);
    }

    /**
     * limit 返回 Stream 的前面 n 个元素；skip 则是扔掉前 n 个元素。以下代码片段使用 limit 方法保留4个元素：
     */
    @Test
    public void limit() {
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        numbers.stream().limit(4).forEach(System.out::println);
    }

    /**
     * sorted 方法用于对流进行排序。以下代码片段使用 sorted 方法进行排序
     */
    @Test
    public void sort() {
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        numbers.stream().sorted().forEach(System.out::println);
    }

    /**
     * distinct主要用来去重，以下代码片段使用 distinct 对元素进行去重
     */
    @Test
    public void distinct() {
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        numbers.stream().distinct().forEach(System.out::println);
    }

    /**
     * 综合运用
     */
    @Test
    public void all() {
        List<String> strings = Arrays.asList("Hollis", "HollisChuang", "hollis", "Hello", "HelloWorld", "Hollis");
        Stream s = strings.stream()
                .filter(string -> string.length() <= 6)
                //.map(i -> i.length())
                .map(String::length)
                .sorted()
                .limit(3)
                .distinct();
        s.forEach(System.out::println);
    }

    /**
     * forEach 来迭代流中的每个数据
     */
    @Test
    public void forEach() {
        Random random = new Random();
        random.ints().limit(10).forEach(System.out::println);
    }

    /**
     * count用来统计流中的元素个数。
     */
    @Test
    public void count() {
        List<String> strings = Arrays.asList("Hollis", "HollisChuang", "hollis", "Hollis666", "Hello", "HelloWorld", "Hollis");
        System.out.println(strings.stream().count());
    }

    /**
     * collect就是一个归约操作，可以接受各种做法作为参数，将流中的元素累积成一个汇总结果：
     */
    @Test
    public void collect() {
        List<String> strings = Arrays.asList("Hollis", "HollisChuang", "hollis", "Hollis666", "Hello", "HelloWorld", "Hollis");
        //strings = strings.stream().filter(string -> string.startsWith("Hollis")).collect(Collectors.toList());
        strings = strings.stream().filter(string -> string.startsWith("Hollis")).collect(Collectors.toCollection(LinkedList::new));
        System.out.println(strings);
    }


    /**
     * ::用法
     * 访问静态方法 Something::startsWith;
     * 访问对象方法 something::endWith;
     * 访问构造方法 Something::new;
     */


}
