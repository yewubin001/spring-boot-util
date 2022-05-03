package com.example.springboot.tools.debug;

/**
 * @Auther: 59315
 * @Date: 2021/4/2 21:55
 * @Description:
 */
public class BreakPointDemo {
    public static void main(String[] args) {
//        line();
       detailLine();
//        method();
//        exception();
//         field();
    }

    // 1. 行断点
    public static void line() {
        System.out.println("this is the line break point");
    }

    // 2. 详细断点（源断点）,shift + 鼠标单击/ ctrl + shift + F8
    // 默认黄色断点并不会断
    public static void detailLine() {
        System.out.println("this is the detail line break point");
    }

    // 3.1 方法断点：断点打在方法上，执行的时候会在方法的第一行和返回的最后一行都会停顿（查看方法返回值）
    // 3.2 接口断点：断点打在接口的方法上，自动跳转实现类方法中
    public static void method() {
        System.out.println("this is the method line break point");
        IService iService = new IServiceImpl();
        iService.execute();
    }

    // 4.1 异常断点 | 全局捕获
    // 4.2 ctrl+shift+F8 -> Java exception breakpoints -> NullPointerException 空指针异常会停住
    public static void exception() {
        Object o = null;
        o.toString();
        System.out.println("this line will never be print!");
    }

    // 5. 字段断点 | 读写监控  可以观察字段值的变化
    public static void field() {
        Person person = new Person("field", 10);
        person.setAge(12);
        System.out.println(person);
    }
}
