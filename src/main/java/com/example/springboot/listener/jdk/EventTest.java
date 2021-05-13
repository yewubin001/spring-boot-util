package com.example.springboot.listener.jdk;

/**
 * @description:
 * @author: yewubin
 * @date: 2021/5/13 11:23
 * @version: v1.0
 */
public class EventTest {

    public static void main(String[] args) {
        EventSource eventSource = new EventSource();

        eventSource.addListener(new MonitorListener() {
            @Override
            public void handleEvent(PrintEvent event) {
                event.doEvent();
                if (event.getSource().equals("openWindows")) {
                    System.out.println("doOpen");
                }
                if (event.getSource().equals("closeWindows")) {
                    System.out.println("doClose");
                }
            }
        });
        /**
         * 传入openWindows事件，通知所有的事件监听器 对open事件感兴趣的listener将会执行
         */
        eventSource.notifyListenerEvents(new PrintEvent("openWindows"));
    }

}
