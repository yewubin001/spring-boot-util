package com.example.springboot.design.prototype;

import java.io.*;

/**
 * @version : 1.0
 * @Description: TODO
 * @Auther: ywb
 * @Date: 2020/11/6 14:51
 */
public class Person implements Cloneable, Serializable {
    private static final long serialVersionUID = 1L;

    private String name;

    private String job;

    private Computer computer;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Computer getComputer() {
        return computer;
    }

    public void setComputer(Computer computer) {
        this.computer = computer;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", job='" + job + '\'' +
                ", computer=" + computer +
                '}';
    }

    public Person(String name, String job, Computer computer) {
        this.name = name;
        this.job = job;
        this.computer = computer;
    }

    /**
     * 方式一
     * 重新clone方法，实现深拷贝
     * @return
     */
    @Override
    protected Person clone() {
        Person person = null;
        try {
            person = (Person) super.clone();
            Computer clone = (Computer) person.getComputer().clone();
            person.setComputer(clone);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return person;
    }

    /**
     * 方式二
     * 通过序列化对象实现深拷贝（推荐使用这种方式），需要实现序列化接口Serializable
     *
     * @return
     */
    public Person deepClone() {
        ByteArrayOutputStream bos;
        ByteArrayInputStream bis;
        ObjectOutputStream oos;
        ObjectInputStream ois;
        try {
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(this);

            bis = new ByteArrayInputStream(bos.toByteArray());
            ois = new ObjectInputStream(bis);
            return (Person) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}

class Computer implements Cloneable, Serializable {

    private static final long serialVersionUID = 1L;

    private String color;
    private String brand;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Computer(String color, String brand) {
        this.color = color;
        this.brand = brand;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "Computer{" +
                "color='" + color + '\'' +
                ", brand='" + brand + '\'' +
                '}';
    }
}
