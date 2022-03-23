package com.example.springboot.spring;


import org.springframework.stereotype.Component;

/**
 * @Auther: 59315
 * @Date: 2020/6/8 21:58
 * @Description:
 */
@Component
public class User {

    private String name;
    private int age;

    public void User() {
        System.out.println("new User()");
    }

    //@Autowired
    public void User(Fox fox){
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






