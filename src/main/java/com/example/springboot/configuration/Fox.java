package com.example.springboot.configuration;

/**
 * @Auther: yewub
 * @Date: 2019/2/14 11:54
 * @Description:
 */
public class Fox {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Fox()
    {
        System.out.println("Fox无参构造器");
    }

    public Fox(String name) {
        super();
        this.name = name;
        System.out.println("有参构造器");
    }

    @Override
    public String toString() {
        return "Fox-->    [name=" + name + "]";
    }

    public void init(){
        System.out.println("init");
    }

    public void destory(){
        System.out.println("destory");
    }
}