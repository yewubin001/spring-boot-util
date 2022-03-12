package com.example.springboot.design.行为型模式.observer;

import java.util.Vector;


public class PingtougeAuthor implements Author {
    // 订阅者
    private Vector<Reader> readers;
    // 作者名称
    private String name;
    // 文章
    private String article;

    public PingtougeAuthor(String name) {
        this.name = name;
        readers = new Vector<>();
    }

    @Override
    public void addReader(Reader reader) {
        if (!readers.contains(reader)) {
            readers.add(reader);
        }
    }

    @Override
    public void deleteReader(Reader reader) {
        readers.remove(reader);
    }

    @Override
    public void notifyReader() {
        for (Reader reader : readers) {
            reader.reader(name, article);
        }
    }

    @Override
    public void writeArticle(String article) {
        this.article = article;
        notifyReader();
    }
}
