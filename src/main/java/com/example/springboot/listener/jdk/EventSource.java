package com.example.springboot.listener.jdk;

import java.util.Vector;

/**
 * @description:
 * @author: yewubin
 * @date: 2021/5/13 11:16
 * @version: v1.0
 */
public class EventSource {

    private Vector<MonitorListener> vector = new Vector<>();

    public void addListener(MonitorListener monitorListener) {
        vector.add(monitorListener);
    }

    public void removeListener(MonitorListener monitorListener) {
        int i = vector.indexOf(monitorListener);
        if (i >= 0) {
            vector.remove(i);
        }
    }

    public void notifyListenerEvents(PrintEvent event) {
        for (MonitorListener monitorListener : vector) {
            monitorListener.handleEvent(event);
        }
    }
}
