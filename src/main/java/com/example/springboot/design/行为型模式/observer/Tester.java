package com.example.springboot.design.observer;

/**
 * 观察者的结构
 * <p>
 * 抽象主题（Subject）角色：抽取出了主题所需要定义的接口，比如我们的Author类
 * 具体主题（Concrete Subject）角色：具体的主题实现者，该类必须继承抽象主题，比如我们的PingtougeAuthor
 * 抽象观察者（Observer）角色：观察者抽象类，定义观察者需要的接口，比如我们的Reader
 * 具体观察者（Concrete Observer）角色：具体的观察者，做这具体业务的类，比如我们的三个订阅者
 */
public class Tester {
    public static void main(String[] args) {
        PingtougeAuthor pingtougeAuthor = new PingtougeAuthor("平头哥");

        ReaderA readerA = new ReaderA("张三");
        ReaderB readerB = new ReaderB("李四");

        pingtougeAuthor.addReader(readerA);

        pingtougeAuthor.addReader(readerB);

        pingtougeAuthor.deleteReader(readerB);

        pingtougeAuthor.writeArticle("庆祝新中国成立70周年！");

    }
}
