package com.example.springboot.zookeeper;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @Author yewubin
 * zk连接器，用于监听配置节点的变化
 * 1.连接zk获取最新的数据库ip信息；
 * 2.给configration加上watch
 *
 * Date 2022-03-12 22:56
 */
public class ZookeeperConnector implements Runnable{

    public static final String CONF_PATH = "/configration";
    public static final String ZOOKEEPER_IP_PORT = "localhost:2181";

    // 初始化zk的连接
    private ZkClient client = new ZkClient(ZOOKEEPER_IP_PORT, 1000, 1000, new MyZKSerializer());

    private static Logger logger = LoggerFactory.getLogger(ZookeeperConnector.class);

    private String dbIp;


    @Override
    public void run() {

        // 读取初始化配置
        dbIp = client.readData(CONF_PATH);

        logger.info(Thread.currentThread().getName() + "获取配置信息：" + dbIp);

        // 定义一个监听器
        IZkDataListener listener = new IZkDataListener() {

            @Override
            public void handleDataDeleted(String s) throws Exception {
                logger.info("-----------node deleted!-------------");
            }

            // 当配置节点发生变化时，客户端获取最新的配置信息
            @Override
            public void handleDataChange(String dataPath, Object data) throws Exception {
                if (data == null) {
                    return;
                }
                if (!dbIp.equals(data.toString())) {
                    dbIp = client.readData(CONF_PATH);
                    logger.info(Thread.currentThread().getName() + "捕获配置变化事件，获取到最新的配置信息为：" + dbIp);
                }
            }
        };

        // 给配置节点增加监听器
        client.subscribeDataChanges(CONF_PATH, listener);

    }
}
