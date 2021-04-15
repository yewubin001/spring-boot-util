package com.example.springboot;

import org.openjdk.jol.info.ClassLayout;

/**
 * @Auther: 59315
 * @Date: 2021/4/11 14:22
 * @Description: new Object 占多少字节
 * <p>
 * 使用 openjdk jar包查看对象头信息
 */
public class JOLTest {
    public static class T {
        int a;
        boolean b;
        String str = "123123123";
    }

    public static void main(String[] args) {
        T t = new T();
        System.out.println(ClassLayout.parseInstance(t).toPrintable());

        synchronized (t) {
            System.out.println(ClassLayout.parseInstance(t).toPrintable());
        }
    }
}
