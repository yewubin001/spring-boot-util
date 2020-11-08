package com.example.springboot.design.builder;
/**
 * @Auther: 59315
 * @Date: 2020/11/5 22:28
 * @Description: 
 */
public class Client {

    public static void main(String[] args) {
        ProductBuilder builder = new DefaultProductBuilder();
        Director director = new Director(builder);
        Product product = director.makeProduct("母猪", "主妇", "part1", "part2");
        System.out.println(product);
    }

}
