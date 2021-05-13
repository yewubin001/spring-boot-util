package com.example.springboot.listener.jdk;

import java.util.EventObject;

/**
 * @description:
 * @author: yewubin
 * @date: 2021/5/13 11:15
 * @version: v1.0
 */
public class PrintEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public PrintEvent(Object source) {
        super(source);
    }

    public void doEvent() {
        System.out.println("通知一个事件源 source：" + this.source);
    }
}
