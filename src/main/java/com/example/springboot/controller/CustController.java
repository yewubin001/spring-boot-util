package com.example.springboot.controller;

import com.example.springboot.aspect.UserAccess;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yewub
 */
@RestController
public class CustController {

    @RequestMapping("/listStudent")
    @UserAccess(value = "listStudent", needLogin = false)
    public String listStudent() {
        return "listStudent";
    }

}