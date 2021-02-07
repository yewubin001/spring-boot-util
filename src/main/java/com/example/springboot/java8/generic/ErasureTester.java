package com.example.springboot.java8.generic;
/**
 * @Auther: 59315
 * @Date: 2021/2/6 15:57
 * @Description: 
 */
public class ErasureTester {

    private static class A<T extends Number> {
        private T size;
        public A(T size) {
            this.size = size;
        }
        public T getSize() {
            return size;
        }
    }
    public static void main(String[] args) {
        A<Integer> a = new A<>(50);
        int size = a.getSize();
        System.out.println(size);

        A a1 = a; // 类型擦除
        Number size1 = a1.getSize();
        System.out.println(size1);
    }
}
