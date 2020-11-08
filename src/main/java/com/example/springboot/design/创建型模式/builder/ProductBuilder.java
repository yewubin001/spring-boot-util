package com.example.springboot.design.builder;

/**
 * @Auther: 59315
 * @Date: 2020/11/5 22:15
 * @Description:
 */
public interface ProductBuilder {

    void productName(String productName);

    void companyName(String companyName);

    void part1(String part1);

    void part2(String part2);

    Product build();
}
