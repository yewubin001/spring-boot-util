package com.example.springboot.java8.io;

import java.io.*;

/**
 * @version : 1.0
 * @Description: TODO
 * @Auther: ywb
 * @Date: 2020/11/9 9:59
 */
public class ObjectStream {
    public static void main(String[] args) {
        Person2 pe;
        try (FileOutputStream out = new FileOutputStream("D:/obj.txt"); ObjectOutputStream oos = new ObjectOutputStream(out)) {
            Person2 per = new Person2("微软", 14);
            pe = new Person2("金山", 20);
            oos.writeObject(per);
            oos.writeObject(pe);
            //解决EOF的关键，加入一个空的对象
            oos.writeObject(null);
            System.out.println("添加成功");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (FileInputStream in = new FileInputStream("D:/obj.txt");
             ObjectInputStream ois = new ObjectInputStream(in)) {
            Object obj;
            while ((obj = ois.readObject()) != null) {
                Person2 s = (Person2) obj;
                System.out.println(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Person2 implements Serializable {

    public static final long serialVersionUID = -1L;
    private String name;
    private /*transient*/ int age;

    public Person2(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getNmae() {
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
        return "姓名：" + name + "  性别:" + age;
    }

}