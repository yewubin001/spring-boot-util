package com.example.springboot.configuration;

/**
 * @Auther: yewub
 * @Date: 2019/2/14 11:54
 * @Description:
 */
public class Book {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Book()
    {
        System.out.println("Book无参构造器");
    }

    public Book(String name) {
        super();
        this.name = name;
        System.out.println("有参构造器");
    }

    @Override
    public String toString() {
        return "Book-->    [name=" + name + "]";
    }
}