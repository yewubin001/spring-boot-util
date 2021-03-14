package com.example.springboot;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeSet;

public class Robot {
    public static void main(String[] args) {
        TreeSet<String> objects = new TreeSet<>(Comparator.comparingInt(String::length));
        objects.add("a");
        objects.add("ab");
        objects.add("abc");
        objects.add("abcd");

        System.out.println(objects.comparator());
        System.out.println(objects.first());

        System.out.println(objects.headSet("abc"));

        Person p11= new Person("yewubin", 32, 1);
        Person p22 = new Person("guikaili", 29, 0);

        Queue queue = new PriorityQueue((o1, o2) -> {
            Person p1 = (Person) o1;
            Person p2 = (Person) o2;
            return p1.getAge() - p2.getAge();
        });

        queue.add(p11);
        queue.add(p22);
        System.out.println(queue);

    }
}