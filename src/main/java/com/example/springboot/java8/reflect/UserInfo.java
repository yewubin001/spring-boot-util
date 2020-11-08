package com.example.springboot.java8.reflect;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserInfo {
    /**
     * 登录账号
     */
    @JsonProperty("usrCde")
    private String login;
    /**
     * 姓名
     */
    @JsonProperty("usrName")
    private String firstName;

    public UserInfo(String login, String firstName) {
        this.login = login;
        this.firstName = firstName;
    }

    /**
     * 转大写
     *
     * @param login
     * @param firstName
     * @return
     */
    public String toUpper(String login, String firstName) {
        return login.toUpperCase() + firstName.toUpperCase();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "login='" + login + '\'' +
                ", firstName='" + firstName + '\'' +
                '}';
    }
}
