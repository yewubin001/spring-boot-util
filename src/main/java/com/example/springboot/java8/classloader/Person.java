package com.example.springboot.java8.classloader;

/**
 * @Author yewubin
 * Date 2021-04-15 23:12
 *
 *
 */
public class Person {
    private String name;

    public Person() {

    }

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return "I am a person, my name is " + name;
    }
}