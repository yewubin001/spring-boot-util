package com.example.springboot.bingfa;
/**
 * @Auther: 59315
 * @Date: 2020/4/18 20:19
 * @Description: 
 */

public class AtomicBooleanTest{

    public static ThreadLocal<String> t = ThreadLocal.withInitial(() -> "1111223");

    public static void main(String[] args) {
        System.out.println(t.get());
    }

}
