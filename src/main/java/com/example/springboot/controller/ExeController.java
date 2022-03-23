package com.example.springboot.controller;

import com.example.springboot.configuration.ApplicationProperties;
import com.example.springboot.design.行为型模式.listener.guava.SmsEvent;
import com.example.springboot.utils.SpringContextUtil;
import com.google.common.eventbus.AsyncEventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 测试控制器
 * <p>
 * 使用@Controller 注解，在对应的方法上，视图解析器可以解析return 的jsp,html页面，并且跳转到相应页面；若返回json/Object等内容到页面，则需要加@ResponseBody注解
 * 使用@RestController注解，相当于@Controller+@ResponseBody两个注解的结合，返回json/Object数据不需要在方法前面加@ResponseBody注解了，但使用@RestController这个注解，
 * 就不能返回jsp,html页面，视图解析器无法解析jsp,html页面
 * <p>
 * (该注解是 @Controller 和 @ResponseBody 注解的合体版)
 *
 * @author yewub
 */
@RestController
public class ExeController {

    @Value("${name}")
    private String name;
    @Value("${age}")
    private String age;

    @Autowired
    private ApplicationProperties applicationProperties;

    @Autowired
    private AsyncEventBus asyncEventBus;

    @GetMapping("/context")
    public Map context() {
        //从spring上下文获取
        String name = SpringContextUtil.getProperty("name");
        String bankBillUrl = SpringContextUtil.getProperty("application.flow.bankBillUrl");

        System.out.println("name=" + name + " bankBillUrl=" + bankBillUrl);
        Map map = new HashMap<String, String>(1);
        map.put("now", DateFormat.getDateTimeInstance().format(new Date()));
        map.put("name", this.name);
        map.put("age", age);
        map.put("bankBillUrl", applicationProperties.getBankBillUrl());
        return map;
    }

    @RequestMapping("/envetbus")
    public void envetBus() {
        SmsEvent noticeSmsEvent = new SmsEvent(1L, "15221146305", "我爱你");
        asyncEventBus.post(noticeSmsEvent);
    }
}