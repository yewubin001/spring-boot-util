package com.example.springboot.spring.reflect;
/**
 * @Auther: 59315
 * @Date: 2020/5/24 21:34
 * @Description: 
 */
public class UserController {

    @AutoWire
    private UserService userService;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
