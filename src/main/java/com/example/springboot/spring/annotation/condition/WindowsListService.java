package com.example.springboot.spring.annotation.condition;

/**
 * @Auther: yewub
 * @Date: 2019/4/17 11:05
 * @Description:
 */
public class WindowsListService implements ListService {

    @Override
    public String showListCmd() {
        return "dir";
    }
}
