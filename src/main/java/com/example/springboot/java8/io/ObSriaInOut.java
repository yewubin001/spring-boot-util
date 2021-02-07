package com.example.springboot.java8.io;

import java.io.*;

/**
 * @version : 1.0
 * @Description: TODO
 * @Auther: ywb
 * @Date: 2020/11/9 9:31
 */


public class ObSriaInOut {

    public static void main(String[] args) {
        Person p=out();
        System.out.println(p);
        Person p2=in();
        System.out.println(p2);
    }

    public static Person out()  {
        //创建对象输出流，在使用对象输出流时候必须进行对象序列化，否则会报错java.io.NotSerializableException
        try(FileOutputStream fileOutputStream = new FileOutputStream("d:\\person.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            Person p = new Person("张大帅",22,5000);
            objectOutputStream.writeObject(p);
            return p;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Person in(){
        //创建对象输入流
        try(ObjectInputStream ois=new ObjectInputStream(new FileInputStream("D:/person.txt"))){
            //读取对象流
            Person p=(Person) ois.readObject();
            System.out.println(p.toString());
            return p;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}


class Person implements Serializable{
    private String name;
    private double sal;
    /**无法序列化*/
    private transient int age;

    public Person() {
        super();

    }
    public Person(String name, double sal, int age) {
        super();
        this.name = name;
        this.sal = sal;
        this.age = age;
    }

    @Override
    public String toString() {
        return this.name+"---"+this.age+"---"+this.sal;
    }

}