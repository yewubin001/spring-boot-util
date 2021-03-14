package com.example.springboot;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

/**
 * @Auther: 59315
 * @Date: 2021/2/22 22:31
 * @Description: 
 */
public class MapTest {

    public static void main(String[] args) throws IOException {

        Properties properties = new Properties();

        properties.load(new FileReader("D:\\work\\spring-boot-util\\src\\main\\resources\\application.properties"));

        System.out.println(properties.getProperty("server.port"));

        properties.setProperty("age", "18");

        System.out.println(properties.getProperty("age"));

//        properties.store(new FileWriter(""), "xxxxx");

        TreeMap<Person, String> treeMap = new TreeMap<>(Comparator.comparingInt(Person::getAge));
        treeMap.put(new Person("aa", 3, 3), "aaa");
        treeMap.put(new Person("bb", 2, 2), "bbb");
        treeMap.put(new Person("ccc", 1, 1), "ccc");

        System.out.println(treeMap);

        Map.Entry<Person, String> en = treeMap.firstEntry();
        System.out.println(treeMap.firstKey());

    }

}



