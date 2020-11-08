package com.example.springboot.java8;

public class IntegerDemo {
    public static void main(String[] args) {
        int i = 128;
        Integer i2 = 128;
        Integer i3 = new Integer(128);

        System.out.println("i == i2 = " + (i == i2)); // Integer会自动拆箱为int，所以为true
        System.out.println("i == i3 = " + (i == i3)); // true，理由同上

        /**
         * Integer i = 127 在编译时，会翻译成为 Integer i = Integer.valueOf(100)，而 java 对 Integer类型的 valueOf 的定义如下：
         * java对于-128到127之间的数，会进行缓存。
         * 所以 Integer i = 127 时，会将127进行缓存，下次再写Integer j = 127时，就会直接从缓存中取，就不会new了。
         */
        // 编译时被翻译成：Integer i4 = Integer.valueOf(127);
        Integer i4 = 127;
        Integer i5 = 127;
        System.out.println("i4 == i5 = " + (i4 == i5));// true

        Integer i6 = 128;
        Integer i7 = 128;
        System.out.println("i6 == i7 = " + (i6 == i7));// false

        Integer i8 = new Integer(127);
        System.out.println("i5 == i8 = " + (i5 == i8)); // false

        Integer i9 = new Integer(128);
        Integer i10 = new Integer(128);
        System.out.println("i9 == i10 = " + (i9 == i10)); // false
    }
}