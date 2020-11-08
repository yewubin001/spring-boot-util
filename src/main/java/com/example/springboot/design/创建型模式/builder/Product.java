package com.example.springboot.design.builder;
/**
 * @Auther: 59315
 * @Date: 2020/11/5 22:14
 * @Description: 
 */
public class Product {

    private String productName;

    private String companyName;

    private String part1;

    private String part2;

    public Product() {
    }

    public Product(String productName, String companyName, String part1, String part2) {
        this.productName = productName;
        this.companyName = companyName;
        this.part1 = part1;
        this.part2 = part2;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productName='" + productName + '\'' +
                ", companyName='" + companyName + '\'' +
                ", part1='" + part1 + '\'' +
                ", part2='" + part2 + '\'' +
                '}';
    }
}
