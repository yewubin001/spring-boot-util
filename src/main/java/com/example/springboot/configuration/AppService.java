package com.example.springboot.configuration;

import org.springframework.stereotype.Component;

/**
 * @Auther: yewub
 * @Date: 2019/2/14 14:54
 * @Description:
 */
//@Component
public class AppService {

    public AppService(){}

    public AppService(String str){
        System.out.println(str);
    }

}
