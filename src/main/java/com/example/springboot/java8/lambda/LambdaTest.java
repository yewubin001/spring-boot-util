package com.example.springboot.java8.lambda;

import java.util.Arrays;
import java.util.List;

/**
 * @Auther: yewub
 * @Date: 2019/5/4 21:18
 * @Description:  Lambda表达式和函数式接口
 */
public class LambdaTest {

    public static void main(String[] args) {
        //参数e的类型是由编译器推理得出的，你也可以显式指定该参数的类型
        Arrays.asList("a", "b", "d").forEach(e -> System.out.println(e));

        //Lambda表达式需要更复杂的语句块，则可以使用花括号将该语句块括起来
        Arrays.asList("a", "b", "d").forEach(e -> {
            System.out.print(e);
            System.out.print(e);
        });

        System.out.println();
        //Lambda表达式可以引用类成员和局部变量
        String separator = ",";
        Arrays.asList("a", "b", "d").forEach(e -> System.out.print(e + separator));

        System.out.println();
        //Lambda表达式有返回值，返回值的类型也由编译器推理得出
        //如果Lambda表达式中的语句块只有一行，则可以不用使用return语句
        List<String> list = Arrays.asList("a", "d", "b");
        list.sort((e1, e2) -> e1.compareTo(e2));
        System.out.println(list.toString());

        System.out.println();
        //与上一条意思一样
        Arrays.asList( "a", "b", "d" ).sort( ( e1, e2 ) -> {
            int result = e1.compareTo( e2 );
            return result;
        } );

    }

}
