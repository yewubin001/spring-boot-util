package com.example.springboot.design.行为型模式.observer;

/**
 * 观察者模式的优点
 * <p>
 * 主题与观察者之间松耦合
 * 支撑广播通信：一条消息可以通知给多个人
 * 建立一条触发链：A触发B，B触发C，一条触发链，不过这个需要注意的地方很多
 * <p>
 * 观察者的缺点
 * <p>
 * 当观察者对象很多时，通知的发布会花费很多时间，影响程序的效率
 * 如果采用顺序通知，当某个观察者卡住了，其他的观察者将无法接收到通知
 * 如果在观察者和观察目标之间有循环依赖的话，观察目标会触发它们之间进行循环调用，可能导致系统崩溃
 */
public interface Author {
    // 添加关注者
    void addReader(Reader reader);

    //&emsp;删除关注者
    void deleteReader(Reader reader);

    // 通知关注者
    void notifyReader();

    // 写文章
    void writeArticle(String article);

}
