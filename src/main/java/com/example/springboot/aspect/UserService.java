package com.example.springboot.aspect;

import java.util.List;

/**
 * @version : 1.0
 * @Description: TODO
 * @Auther: ywb
 * @Date: 2020/6/15 15:37
 */
public interface UserService {

    String listStudent(String name);

    List<Object> getUserInfo(String name, int age);
}
