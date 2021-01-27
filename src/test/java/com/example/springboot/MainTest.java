package com.example.springboot;


import com.example.springboot.utils.Util;
import org.hibernate.validator.internal.util.privilegedactions.GetResource;

public class MainTest {
    public static void main(String[] args) {

        System.out.println(Util.getTxt("templates/report.json"));

        System.out.println(GetResource.class.getClassLoader().getResource("templates").getPath());
    }
}