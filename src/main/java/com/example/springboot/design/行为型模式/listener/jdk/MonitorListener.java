package com.example.springboot.design.行为型模式.listener.jdk;

import java.util.EventListener;

/**
 * @description:
 * @author: yewubin
 * @date: 2021/5/13 11:13
 * @version: v1.0
 */
public interface MonitorListener extends EventListener {

    void handleEvent(PrintEvent event);
}
