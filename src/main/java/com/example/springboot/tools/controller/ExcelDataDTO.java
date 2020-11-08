package com.example.springboot.tools.controller;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

/**
 * @Auther: 59315
 * @Date: 2019/11/12 23:53
 * @Description: 
 */
public class ExcelDataDTO extends BaseRowModel {

    @ExcelProperty(value = "姓名",index = 0)
    protected String name;

    @ExcelProperty(value = "年龄",index = 1)
    protected Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "ExcelDataDTO{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
