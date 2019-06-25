package com.example.springboot.annotation;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @Auther: yewub
 * @Date: 2018/11/9 10:38
 * @Description:
 */
public class StudentVO {

    @CustomAnnotation(info = "学生姓名不能为空;")
    private String name;

    @CustomAnnotation(info = "学生性别不能为空;")
    private Integer sex;

//    @Size(max = 11)
//    @NotBlank(message = "电话号码不能为空")
    @CustomAnnotation(info = "电话号码不能为空;")
    private String tel;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
