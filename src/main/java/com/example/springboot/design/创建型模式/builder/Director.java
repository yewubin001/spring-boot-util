package com.example.springboot.design.builder;
/**
 * @Auther: 59315
 * @Date: 2020/11/5 22:23
 * @Description: 
 */
public class Director {

    private ProductBuilder builder;

    public Director(ProductBuilder builder) {
        this.builder = builder;
    }

    public Product makeProduct(String productName, String companyName, String part1, String part2) {
        builder.productName(productName);
        builder.companyName(companyName);
        builder.part1(part1);
        builder.part2(part2);
        Product product = builder.build();
        return product;
    }
}
