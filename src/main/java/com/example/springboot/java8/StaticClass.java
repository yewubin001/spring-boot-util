package com.example.springboot.java8;
/**
 * @Auther: 59315
 * @Date: 2020/11/22 23:37
 * @Description:  测试java中静态常量和静态变量区别的样例，表明两者加载时的区别。
 */
public class StaticClass {

    static {
        System.out.println("StaticClass loading...");
    }

    public static String VALUE = "static value loading";

    public static final String FIANL_VALUE = "fianl value loading";
}

/**
 * 经过编译优化，静态常量 FIANL_VALUE 已经存到常量池中，不会加载StaticClass
 */
class StaticTest{

    public static void main(String[] args) {
        System.out.println("StaticTest ....");
        System.out.println("-------");
        System.out.println(StaticClass.FIANL_VALUE);
        System.out.println("-------");
        System.out.println(StaticClass.VALUE);
    }

}