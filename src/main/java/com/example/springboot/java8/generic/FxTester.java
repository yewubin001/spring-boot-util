package com.example.springboot.java8.generic;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * @Auther: 59315
 * @Date: 2021/2/5 22:00
 * @Description: 
 */
public class FxTester {
    public static void main(String[] args) {
        // 泛型类
        Person<String> person = new Person<>();
        person.setName("zhangsan");
        person.setAddress("中国上海");
        System.out.println(person);

        Person1<String, Integer> person1 = new Person1();
        person1.setName("lisi");
        person1.setAddress(111);
        System.out.println(person1);

        System.out.println("----------泛型方法--------");

        //泛型普通方法
        Person2<String> person2 = new Person2();
        person2.show("李小龙");
        person2.show1(100.00);
        //泛型静态方法（没有返回值）
        Person2.show2(1111);
        //泛型静态方法（有返回值）
        System.out.println(Person2.show3(3.14));

        System.out.println("---------泛型接口---------");

        //泛型接口
        PerInteImpl perInte = new PerInteImpl();
        perInte.show(100);

        System.out.println("---------泛型擦除---------");

        // 泛型擦除测试
        // Java泛型只存在于编译阶段，运行期间泛型是会被擦除的
        // 为什么要使用类型擦除？为了兼容jdk老版本的编码
        Person<String> ps = new Person<>();
        Person<Integer> pi = new Person<>();
        System.out.println(ps.getClass()==pi.getClass());

        System.out.println("--------泛型通配符--------");

        Person<Number> pn = new Person<>();
        Person<Integer> pr = new Person<>();
        pr.setName(111);
        Person<String> pg = new Person<>();
        pg.setName("ppp");
        // pn.show(pr);
        pn.show(pg); // Integer 是 Number的子类
        System.out.println(pn.getName());

        System.out.println("-----------上边界、下边界----------");
        //pn.show2(pg); 报错
        pn.show2(pn);


        ArrayList<Number> list1 = new ArrayList();
        ArrayList<Integer> list2 = new ArrayList();
        list1.add(111); // 可以添加Integer类型
        // method(list2); 报错，因为集合不可以协变，必须一一对应
        list1.addAll(list2);  // 此处使用了泛型类型
        // 以上两个是有区别的

        ArrayList<String> list3 = new ArrayList<>();
        list3.add("11");
        list3.removeIf(o -> o.length()>3);
        System.out.println(list3);
    }

    public static void method(ArrayList<Number> list){

    }
}
