package com.example.springboot.design.observer;

public class ReaderB implements Reader {
    private String name;

    public ReaderB(String name) {
        this.name = name;
    }

    @Override
    public void reader(String authorName, String article) {
        System.out.println(name + "阅读了" + authorName + "发布的" + article);
    }
}
