package com.example.springboot.design.decorator;

/**
 * 以上就是装饰者模式的一个小栗子，讲述了装饰者的基本用法。通过上述的例子，我们可以总结一下装饰者模式的特点。
 * （1）装饰者和被装饰者有相同的接口（或有相同的父类）。
 * （2）装饰者保存了一个被装饰者的引用。
 * （3）装饰者接受所有客户端的请求，并且这些请求最终都会返回给被装饰者（参见韦恩图）。
 * （4）在运行时动态地为对象添加属性，不必改变对象的结构。
 * <p>
 * 使用装饰者模式的最大好处就是其拓展性十分良好，通过使用不同的装饰类来使得对象具有多种多样的属性，灵活性比直接继承好。
 * 然而它也有缺点，那就是会出现很多小类，即装饰类，使程序变得复杂。
 */
public class Tester {
    public static void main(String[] args) {

        Person person = new Teenager();

        person = new Shirt(person);

        person = new Casquette(person);

        System.out.println(person.getDescription() + " ￥" + person.cost());

    }
}
