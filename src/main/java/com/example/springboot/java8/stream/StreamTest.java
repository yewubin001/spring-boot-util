package com.example.springboot.java8.stream;

import org.junit.Test;

import java.math.BigDecimal;
import java.sql.SQLOutput;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Auther: yewub
 * @Date: 2019/5/6 15:18
 * @Description:
 */
public class StreamTest {

    /****************************************************1. 流的常用创建方法******************************************************/

    /**
     * Stream 初始化
     * 1、使用Collection下的 stream() 和 parallelStream() 方法
     * 2、使用Arrays 中的 stream() 方法，将数组转成流
     * 3、 使用Stream中的静态方法：of()、iterate()、generate()
     */
    @Test
    public void stream1() {
        List<String> strings = Arrays.asList("Hollis", "HollisChuang", "hollis", "Hello", "HelloWorld", "Hollis");
        strings.stream().forEach(System.out::println);

        Stream<String> s = Stream.of("Hollis", "HollisChuang", "hollis", "Hello", "HelloWorld", "Hollis");
        s.forEach(System.out::println);

        Stream<Integer> stream2 = Stream.iterate(0, (x) -> x + 2).limit(6);
        stream2.forEach(System.out::println);  // 0 2 4 6 8 10

        Stream<Double> stream3 = Stream.generate(Math::random).limit(2);
        stream3.forEach(System.out::println);

        Arrays.stream(new int[]{1,2,3,4}).forEach(System.out::println);

        Stream.generate(Math::random).limit(2);
    }

    /****************************************************2. 流的中间操作******************************************************/


    /**
     * filter 方法用于通过设置的条件过滤出元素。以下代码片段使用 filter 方法过滤掉空字符串
     * 函数式接口   @FunctionalInterface
     */
    @Test
    public void filter() {
        List<String> strings = Arrays.asList("Hollis", "", "HollisChuang", "H", "hollis");
        strings.stream().filter(s -> !s.isEmpty()).forEach(System.out::println);
//        strings.stream().filter(new Predicate<String>() {
//            @Override
//            public boolean test(String s) {
//                return !s.isEmpty();
//            }
//        }).forEach(new Consumer<String>() {
//            @Override
//            public void accept(String s) {
//                System.out.println(s);
//            }
//        });


    }

    /**
     * limit 返回 Stream 的前面 n 个元素；skip 则是扔掉前 n 个元素。以下代码片段使用 limit 方法保留4个元素：
     */
    @Test
    public void limit() {
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        numbers.stream().skip(3).forEach(System.out::println);
        numbers.stream().limit(4).forEach(System.out::println);
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
     * map：接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
     */
    @Test
    public void map() {
        List<String> list = Arrays.asList("a,b,c", "1,2,3,4");
        list.stream().map(s -> s.replaceAll(",", "")).forEach(System.out::println);
        list.stream().map(String::length).forEach(System.out::println);
    }

    /**
     * flatMap：接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流。
     */
    @Test
    public void flatMap() {
        List<String> list = Arrays.asList("a,b,c", "1,2,3,4");
        Stream<Object> objectStream = list.stream().flatMap(o -> {
            String[] split = o.split(",");
            return Stream.of(split);
        });
        objectStream.forEach(System.out::println);
    }

    /**
     * sorted()：自然排序，流中元素需实现Comparable接口
     * sorted(Comparator com)：定制排序，自定义Comparator排序器
     */
    @Test
    public void sort() {
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        numbers.stream().sorted().forEach(System.out::println);
        numbers.stream().sorted((o1, o2) -> o2-o1).forEach(System.out::println);
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
     * ::用法
     * 访问静态方法 Something::startsWith;
     * 访问对象方法 something::endWith;
     * 访问构造方法 Something::new;
     */


    /****************************************************3. 流的终止操作******************************************************/

    /**
     * 3.1 匹配、聚合操作
     *         allMatch：接收一个 Predicate 函数，当流中每个元素都符合该断言时才返回true，否则返回false
     *         noneMatch：接收一个 Predicate 函数，当流中每个元素都不符合该断言时才返回true，否则返回false
     *         anyMatch：接收一个 Predicate 函数，只要流中有一个元素满足该断言则返回true，否则返回false
     *         findFirst：返回流中第一个元素
     *         findAny：返回流中的任意元素
     *         count：返回流中元素的总个数
     *         max：返回流中元素最大值
     *         min：返回流中元素最小值
     */
    @Test
    public void stop() {

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        List<Boolean> booleans = Arrays.asList(false, true);

        System.out.println(booleans.stream().anyMatch(e -> e == true));

        boolean allMatch = list.stream().allMatch(e -> e > 10);
        boolean noneMatch = list.stream().noneMatch(e -> e > 10);
        boolean anyMatch = list.stream().anyMatch(e -> e > 4);
        System.out.println(String.format("%s. %s. %s", allMatch, noneMatch, anyMatch));

        Integer integer = list.stream().findFirst().get();
        Integer integer1 = list.stream().findAny().get();

        System.out.println(String.format("%s, %s", integer, integer1));

        long count = list.stream().count();
        Integer i1 = list.stream().max(Integer::compareTo).get();
        Integer i2 = list.stream().min(Integer::compareTo).get();
        System.out.println(String.format("%s. %s. %s", count, i1, i2));

    }


    /**
     * 3.2 规约操作
     * reduce
     */
    @Test
    public void reduce() {
        List<BigDecimal> intList = Arrays.asList(new BigDecimal(1), new BigDecimal(2), new BigDecimal(3));
        Optional<BigDecimal> reduceOption = intList.stream().reduce(BigDecimal::add);
        BigDecimal reduce = intList.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println(reduce);


        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Integer count = list.stream().reduce((x1, x2) -> x1 + x2).get();
        System.out.println(count);

        Integer sum = list.stream().reduce(10, (x1, x2) -> x1 + x2);
        System.out.println(sum);
    }


    /**
     * 3.3 收集操作
     * collect就是一个归约操作，可以接受各种做法作为参数，将流中的元素累积成一个汇总结果：
     */
    @Test
    public void collect() {
        List<String> strings = Arrays.asList("Hollis", "HollisChuang", "hollis", "Hollis666", "Hello", "HelloWorld", "Hollis");
        //strings = strings.stream().filter(string -> string.startsWith("Hollis")).collect(Collectors.toList());
        strings = strings.stream().filter(string -> string.startsWith("Hollis")).collect(Collectors.toCollection(LinkedList::new));
        System.out.println(strings);
        List<String> colors = Stream.of("blue", "red", "yellow").collect(Collectors.toList());
    }

    @Test
    public void collect2() {

        Student s1 = new Student("aa", 10, 1);
        Student s2 = new Student("bb", 20, 2);
        Student s3 = new Student("cc", 10, 3);
        List<Student> list = Arrays.asList(s1, s2, s3);

        //转成list
        List<Integer> ageList = list.stream().map(Student::getAge).collect(Collectors.toList()); // [10, 20, 10]

        //转成set
        Set<Integer> ageSet = list.stream().map(Student::getAge).collect(Collectors.toSet()); // [20, 10]

        //转成map,注:key不能相同，否则报错
        Map<String, Integer> studentMap = list.stream().collect(Collectors.toMap(Student::getName, Student::getAge)); // {cc=10, bb=20, aa=10}

        //字符串分隔符连接
        String joinName = list.stream().map(Student::getName).collect(Collectors.joining(",", "(", ")")); // (aa,bb,cc)

        //聚合操作
        //1.学生总数
        Long count = list.stream().collect(Collectors.counting()); // 3
        //2.最大年龄 (最小的minBy同理)
        Integer maxAge = list.stream().map(Student::getAge).collect(Collectors.maxBy(Integer::compare)).get(); // 20
        //3.所有人的年龄
        Integer sumAge = list.stream().collect(Collectors.summingInt(Student::getAge)); // 40
        //4.平均年龄
        Double averageAge = list.stream().collect(Collectors.averagingDouble(Student::getAge)); // 13.333333333333334
        // 带上以上所有方法
        DoubleSummaryStatistics statistics = list.stream().collect(Collectors.summarizingDouble(Student::getAge));
        System.out.println("count:" + statistics.getCount() + ",max:" + statistics.getMax() + ",sum:" + statistics.getSum() + ",average:" + statistics.getAverage());

        //分组
        Map<Integer, List<Student>> ageMap = list.stream().collect(Collectors.groupingBy(Student::getAge));
        //多重分组,先根据类型分再根据年龄分
        Map<Integer, Map<Integer, List<Student>>> typeAgeMap = list.stream().collect(Collectors.groupingBy(Student::getType, Collectors.groupingBy(Student::getAge)));

        //分区
        //分成两部分，一部分大于10岁，一部分小于等于10岁
        Map<Boolean, List<Student>> partMap = list.stream().collect(Collectors.partitioningBy(v -> v.getAge() > 10));

        //规约
        Integer allAge = list.stream().map(Student::getAge).collect(Collectors.reducing(Integer::sum)).get(); //40
    }

    class Student {
        private String name;
        private int age;
        private int type;

        public Student(String name, int age, int type) {
            this.name = name;
            this.age = age;
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
