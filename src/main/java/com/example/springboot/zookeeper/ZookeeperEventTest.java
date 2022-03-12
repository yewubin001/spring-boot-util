package com.example.springboot.zookeeper;

import org.I0Itec.zkclient.ZkClient;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author yewubin
 * 实现分布式配置中心
 * Date 2022-03-12 23:12
 *
 * 启动zkui ，访问 http://localhost:9090/home?zkPath=/
 */
public class ZookeeperEventTest {

    public static final String CONF_PATH = "/configration";
    public static final String ZOOKEEPER_IP_PORT = "localhost:2181";

    @Before
    public void init(){
        ZkClient client = new ZkClient(ZOOKEEPER_IP_PORT, 1000, 1000, new MyZKSerializer());
        if (client.exists(CONF_PATH)) {
            client.delete(CONF_PATH);
        }
        // 创建"/configration"节点
        client.createPersistent(CONF_PATH);
        // 写入初始值
        client.writeData(CONF_PATH, "192.168.1.1");
    }

    //模拟分布式环境，有五个应用要读取配置中心的配置信息
    @Test
    public void zkEventTest() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.submit(new ZookeeperConnector());
        executorService.submit(new ZookeeperConnector());
        executorService.submit(new ZookeeperConnector());
        executorService.submit(new ZookeeperConnector());
        executorService.submit(new ZookeeperConnector());
        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
