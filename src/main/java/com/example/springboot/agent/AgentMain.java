package com.example.springboot.agent;

import java.lang.instrument.Instrumentation;

/**
 * @Auther: 59315
 * @Date: 2020/6/7 11:44
 * @Description: 
 */
public class AgentMain {

    // javaaget 入口方法
    public static void premain(String art, Instrumentation instrumentation){
        System.out.println("hello javaagent");
    }

}
