package com.example.springboot;


import com.example.springboot.java8.Converter;

public class UTest {
    public static void main(String[] args) {

        Converter<String, Integer> converter = Integer::valueOf;
        converter.convert("123");
        System.out.println(converter);
    }
}