package com.example.springboot.design.builder;
/**
 * @Auther: 59315
 * @Date: 2020/11/5 22:21
 * @Description: 
 */
public class DefaultProductBuilder implements ProductBuilder {
    private String productName;

    private String companyName;

    private String part1;

    private String part2;

    @Override
    public void productName(String productName) {
        this.productName = productName;
    }

    @Override
    public void companyName(String companyName) {
        this.companyName = companyName;

    }

    @Override
    public void part1(String part1) {
        this.part1 = part1;

    }

    @Override
    public void part2(String part2) {
        this.part2 = part2;
    }

    @Override
    public Product build() {
        return new Product(this.productName, this.companyName, this.part1, this.part2);
    }
}
