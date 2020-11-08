package com.example.springboot.design.strategy;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @Auther: yewub
 * @Date: 2019/2/15 17:11
 * @Description: 实际上在通常的做法中，这两种是可以结合起来的，利用工厂模式来创建对象
 */
public class StrategyMain {

    public static void main(String[] args) {
        int num1 = 100;
        int num2 = 200;

        Context context = new Context(new AddStrategy());
        context.operat(num1, num2);

        Context context1 = new Context(new SubtractionStrategy());
        context1.operat(num2, num1);


        ArrayList<String> objects = Lists.newArrayList();
        objects.add("zz");
        objects.add("aa");
        Collections.sort(objects, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        objects.stream().forEach(e -> System.out.println(e));
    }
}
