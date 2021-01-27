package com.example.springboot.design.创建型模式.prototype;

/**
 * @version : 1.0
 * @Description: TODO
 * @Auther: ywb
 * @Date: 2020/11/6 14:53
 */
public class Client {

    public static void main(String[] args) {
        Person p = new Person("特拉普", "脱口秀演员", new Computer("红色", "苹果"));
        Person clone = p.clone();

        Person deepClone = p.deepClone();
        p.getComputer().setColor("黑色");
        System.out.println(p);
        System.out.println(clone);
        System.out.println(deepClone);


    }

}
