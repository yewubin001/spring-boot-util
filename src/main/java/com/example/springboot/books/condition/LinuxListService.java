package com.example.springboot.books.condition;

import javax.validation.constraints.Max;

/**
 * @Auther: yewub
 * @Date: 2019/4/17 11:06
 * @Description:
 */
public class LinuxListService implements ListService {

    @Override
    public String showListCmd() {
        return "ls";
    }

}
