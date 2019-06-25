package com.example.springboot.mock;

/**
 * @Auther: yewub
 * @Date: 2019/2/25 14:44
 * @Description:
 */
public class Person {

    private final int id;

    private final String name;

    public Person (int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}