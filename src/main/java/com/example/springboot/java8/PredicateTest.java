package com.example.springboot.java8;

import org.junit.Test;

import java.util.function.Predicate;

/**
 * @author Magfin
 * @version : 1.0
 * @Description: TODO
 * @Auther: ywb
 * @Date: 2020/3/17 18:06
 */
public class PredicateTest {

    //test()
    @Test
    public void test() {
        PredicateTest predicate = new PredicateTest();
        /** - 1.判断传入的字符串的长度是否大于5 */
        System.out.println(predicate.judgeConditionByFunction(12345, c -> String.valueOf(c).length() > 5));
        /** - 2.判断传入的参数是否是奇数 */
        System.out.println(predicate.judgeConditionByFunction(4, c -> c % 2 == 0));
        /** - 3.判断数字是否大于10 */
        System.out.println(predicate.judgeConditionByFunction(-1, c -> c > 10));
    }

    public boolean judgeConditionByFunction(int value, Predicate<Integer> predicate) {
        return predicate.test(value);
    }

    //and() 等同于我们的逻辑与&&,存在短路特性
    @Test
    public void and() {
        PredicateTest predicate = new PredicateTest();
        System.out.println(predicate.testAndMethod("zhangsan",
                stringOne -> stringOne.equals("zhangsan"), stringTwo -> stringTwo.length() > 5, stringThree -> stringThree.length() % 2 == 0));
    }

    /**
     * @param stringOne      待判断的字符串
     * @param predicateOne   断定表达式1
     * @param predicateTwo   断定表达式2
     * @param predicateThree 断定表达式3
     * @return 是否满足两个条件
     */
    public boolean testAndMethod(String stringOne, Predicate<String> predicateOne, Predicate<String> predicateTwo, Predicate<String> predicateThree) {
        return predicateOne.and(predicateTwo).and(predicateThree).test(stringOne);
    }


    //nagate() 等同于我们的逻辑非
    @Test
    public void negate() {
        PredicateTest predicate = new PredicateTest();
        System.out.println(predicate.testNageteMethod("zhangsan", stringOne -> stringOne.equals("zhangsan")));
    }

    public boolean testNageteMethod(String value, Predicate<String> predicate) {
        return predicate.negate().test(value);
    }

    //or() 等同于我们的逻辑或
    @Test
    public void or() {
        PredicateTest predicate = new PredicateTest();
        System.out.println(predicate.testOrMethod("zhangsan", stringOne -> stringOne.equals("zhangsan2"), s2 -> s2.length() > 10));
    }

    public boolean testOrMethod(String value, Predicate<String> predicate1, Predicate<String> predicate2) {
        return predicate1.or(predicate2).test(value);
    }
}
