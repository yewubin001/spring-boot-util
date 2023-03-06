package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DemoApplicationTests {

    public static void main(String[] args) {
        //声明一个List集合
        List<Person> list = new ArrayList();
        list.add(new Person("1001", "小A"));
        list.add(new Person("1001", "小B"));
        list.add(new Person("1003", "小C"));
        System.out.println(list);

        // 1、将list转换map
        // 报错 Duplicate key 小A
        // Map<String, String> map = list.stream().collect(Collectors.toMap(Person::getId,Person::getName));

        // 2、转换过程中的两个问题
        // 2.1 key重复
        // 重复时用后面的value 覆盖前面的value
        Map<String, String> map2 = list.stream().collect(Collectors.toMap(Person::getId, Person::getName, (key1, key2) -> key2));
        System.out.println(map2);
        // 重复时将前面的value 和后面的value拼接起来
        Map<String, String> map3 = list.stream().collect(Collectors.toMap(Person::getId, Person::getName, (key1, key2) -> key1 + "," + key2));
        System.out.println(map3);
        // 重复时将重复key的数据组成集合
        Map<String, List<String>> map4 = list.stream().collect(Collectors.toMap(Person::getId,
                p -> {
                    List<String> getNameList = new ArrayList<>();
                    getNameList.add(p.getName());
                    return getNameList;
                },
                (List<String> value1, List<String> value2) -> {
                    value1.addAll(value2);
                    return value1;
                }
        ));
        System.out.println(map4);

        // 2.1 valve为null 在转换流中加上判空，即便value为空,依旧输出
    }

}

class Person {
    private String id;
    private String name;

    public Person(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
