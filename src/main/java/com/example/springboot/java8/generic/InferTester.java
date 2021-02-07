package com.example.springboot.java8.generic;
/**
 * @Auther: 59315
 * @Date: 2021/2/6 15:35
 * @Description:
 */
public class InferTester {
    private static class A<T> {
        public static <E> A<E> test1() {
            System.out.println("test1");
            return new A<>();
        }
        public static <E> A<E> test2(E e, A<E> a) {
            System.out.println("test2");
            return new A<>();
        }
        public T head(){
            System.out.println("test3");
            return null;
        }
    }
    public static void main(String[] args) {
        // 下面两行代码一样
        A<String> a1 = A.test1();
        A<String> a2 = A.<String>test1();
        // 下面两行代码一样
        A.test2(56, A.test1());
        A.test2(56, A.<Integer>test1());
        // 会经过两次类型推断，因为自动类型推断只能推断一次，所以报错
        //String str = A.test1().head();
    }
}
