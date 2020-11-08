package com.example.springboot.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;

/**
 * @Auther: 59315
 * @Date: 2020/7/23 22:52
 * @Description: zookeeper
 */
public class ZookeeperProSync implements Watcher {
    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static ZooKeeper zk = null;
    private static Stat stat = new Stat();
    public static void main(String[] args) throws Exception {
        // zk配置数据存放路径
        String path = "/username";
        // 连接zk并且注册一个默认的监听器
        zk = new ZooKeeper("192.168.149.3:2181,192.168.149.3:2182,192.168.149.3:2183",
                5000, new ZookeeperProSync());
        // 等待zk连接成功的通知
        countDownLatch.await();
        // 获取path目录节点的配置数据，并且注册默认的监听器（一次性的）
        System.out.println(new String(zk.getData(path, true, stat)));

        Thread.sleep(Integer.MAX_VALUE);
    }

    public void process(WatchedEvent event) {
        if (Event.KeeperState.SyncConnected == event.getState()) { // zk 连接成功通知事件
            if (Event.EventType.None == event.getType() && event.getPath() == null){
                countDownLatch.countDown();
            } else if(Event.EventType.NodeDataChanged == event.getType()) { //zk目录节点数据变化通知事件
                try {
                    // 监听是一次性的
                    System.out.println("配置已修改，新值为：" + new String(zk.getData(event.getPath(), true, stat)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
