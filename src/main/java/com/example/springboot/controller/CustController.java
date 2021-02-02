package com.example.springboot.controller;

import com.example.springboot.aop.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yewub
 */
@RestController
public class CustController {

    @Autowired
    private UserService userService;

    @RequestMapping("/list-student")
    public String listStudent(String name) {
        return userService.listStudent(name);
    }


    @RequestMapping("/user-info")
    public String userInfo(String name, int age) {
        userService.getUserInfo(name, age);
        return "success";
    }
}