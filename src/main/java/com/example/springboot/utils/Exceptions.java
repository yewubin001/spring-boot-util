package com.example.springboot.utils;

/**
 * @Auther: yewub
 * @Date: 2019/5/27 15:51
 * @Description:
 */
public class Exceptions {
    public static RuntimeException unchecked(Throwable ex) {
        if (ex instanceof RuntimeException) {
            return (RuntimeException) ex;
        } else {
            return new RuntimeException(ex);
        }
    }
}