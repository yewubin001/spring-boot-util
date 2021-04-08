package com.example.springboot.tools.debug;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 断点调试：进阶
 */

public class DebugAdvance {
    // 1. 条件表达式
    public static void conditions() {
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }
        
    }

    // 2. 打印堆栈信息
    // ctrl + shift + F8 -> Log：BreakPoint hit message ; Stack trace
    public static void printStackTrace() {
        ArrayList list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        System.out.println(list);
    }

    // 3. 表达式解析
    public static void evaluate() {
        System.out.println("evaluate");
        Person person = new Person("byBIN", 20);
        Arrays.asList(1, 2, 3, 4)
                .stream().map(x -> x * 2)
                .collect(Collectors.toList());
    }

    // 4. 避免操作资源 | force return
    // 断点后面的代码不想被执行：frames -> 找到线程下被执行的方法 右键 -> force return
    public static void saveResource() {
        System.out.println("shit happens");

        System.out.println("save to db");
        System.out.println("save to redis");
        System.out.println("send message to mq for money payout");
    }

    // 5. 快捷键、图标含义
    public static void keysExplain() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("hello world");
    }

    // 6. Java8 Stream chain
    public static void streamDebug() {
        Arrays.asList(1, 2, 3, 45)
                .stream()
                .filter(i -> i % 2 == 0 || i % 3 == 0)
                .map(i -> i * 2)
                .forEach(System.out::print);
    }

    public static void main(String[] args) {
//        conditions();
//        printStackTrace();
//        evaluate();
//        saveResource();
//        keysExplain();
        streamDebug();
    }
}
