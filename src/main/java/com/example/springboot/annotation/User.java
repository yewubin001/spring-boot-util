package com.example.springboot.annotation;

/**
 * @version : 1.0
 * @Description: TODO
 * @Auther: ywb
 * @Date: 2020/6/9 11:49
 */
//@Component
public class User {

    private String name;
    private int age;

    public User() {
        System.out.println("new User()");
    }

    // 指定调用构造方法
    //@Autowired
    public User(Fox fox) {
        System.out.println("new User(Fox fox)");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
