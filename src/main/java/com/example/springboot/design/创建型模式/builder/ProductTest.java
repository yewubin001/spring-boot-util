package com.example.springboot.design.builder;

/**
 * @Auther: 59315
 * @Date: 2020/11/5 22:30
 * @Description:
 */
public class ProductTest {

    public static void main(String[] args) {
        Product2.Builder builder = new Product2.Builder().productName("iphone12").companyName("苹果").part1("part1").part2("part2");

        Product2 product2 = builder.builder();

        System.out.println(product2);
    }
}

class Product2 {

    private final String productName;
    private final String companyName;
    private final String part1;
    private final String part2;

    public Product2(String productName, String companyName, String part1, String part2) {
        this.productName = productName;
        this.companyName = companyName;
        this.part1 = part1;
        this.part2 = part2;
    }

    static class Builder {
        private String productName;
        private String companyName;
        private String part1;
        private String part2;

        public Builder productName(String productName){
            this.productName = productName;
            return this;
        }
        public Builder companyName(String companyName){
            this.companyName = companyName;
            return this;
        }
        public Builder part1(String part1){
            this.part1 = part1;
            return this;
        }
        public Builder part2(String part2){
            this.part2 = part2;
            return this;
        }

        public Product2 builder(){
            return new Product2(productName, companyName, part1, part2);
        }

    }


    @Override
    public String toString() {
        return "Product2{" +
                "productName='" + productName + '\'' +
                ", companyName='" + companyName + '\'' +
                ", part1='" + part1 + '\'' +
                ", part2='" + part2 + '\'' +
                '}';
    }
}
