package com.example.springboot.design.行为型模式.listener.spring;

import lombok.NonNull;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

/**
 * 监听器的创建
 * 实现ApplicationListener 接口即可， ApplicationListener 中的泛型指定为对应的事件
 * 切记要把该监听器注入到Spring中哦 !!!
 */
public class ReaderListener {

    @NonNull
    private String name;

    private Object article;

    public ReaderListener(String name) {
        this.name = name;
    }

    /**
     * Handle an application event.
     * @param event the event to respond to
     */
    @Async
    @EventListener
    public void onApplicationEvent(JavaStackEvent event) {
        // 更新文章
        updateArticle(event);
    }
    private void updateArticle(JavaStackEvent event) {
        this.article = event.getSource();
        System.out.printf("我是读者：%s，文章已更新为：%s\n", name, article);
    }
}
