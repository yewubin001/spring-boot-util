package com.example.springboot.aspect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @version : 1.0
 * @Description: TODO
 * @Auther: ywb
 * @Date: 2020/6/15 15:38
 */
@Service
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    @EnabledUserAccess(value = "listStudent", needLogin = false)
    public String listStudent(String name) {
        logger.info("listStudent is {}", name);
        return "success";
    }

    @Override
    @EnabledUserAccess(value = "userInfo", needLogin = false)
    public List<Object> getUserInfo(String name, int age) {
        logger.info("name = {}, age = {}", name, age);
        ArrayList<Object> list = new ArrayList<>();
        list.add(name);
        list.add(age);
        return list;
    }
}
