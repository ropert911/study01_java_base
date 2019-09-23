package com.xq.zookeeper;

import java.io.IOException;

import org.apache.zookeeper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static java.lang.Thread.sleep;

public class ZookeeperDemo{
    private static final Logger LOGGER = LoggerFactory.getLogger(ZookeeperDemo.class);
    private static final int TIME_OUT = 3000;
    private static final String HOST = "192.168.17.128:2181";

    public static void main(String arg[]) {
        try {
            // 创建一个与服务器的连接
            ZooKeeper zk = new ZooKeeper(HOST, TIME_OUT, new Watcher() {
                // 监控所有被触发的事件
                @Override
                public void process(WatchedEvent event) {
//                    LOGGER.info("触发路径 {} 事件 {}！============", event.getPath(), event.getType());
                }
            });
            // 创建一个目录节点和子目录结点
            LOGGER.info("创建[testRootPath]");
            zk.create("/testRootPath", "testRootData".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            LOGGER.info("创建[/testRootPath/testChildPathOne]");
            zk.create("/testRootPath/testChildPathOne", "testChildDataOne".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

            //查询
            LOGGER.info("[/testRootPath]的数据 {}", new String(zk.getData("/testRootPath", true, null)));
            LOGGER.info("[/testRootPath]的子目录 {}" ,zk.getChildren("/testRootPath", true));
            LOGGER.info("[/testRootPath/testChildPathOne]的数据 {}", new String(zk.getData("/testRootPath/testChildPathOne", true, null)));

            // 修改子目录节点数据
            zk.setData("/testRootPath/testChildPathOne", "modifyChildDataOne".getBytes(), -1);
            LOGGER.info("[/testRootPath/testChildPathOne]修改后数据 {}", new String(zk.getData("/testRootPath/testChildPathOne", true, null)));

            // 创建另外一个子目录节点
            zk.create("/testRootPath/testChildPathTwo", "testChildDataTwo".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            LOGGER.info("[/testRootPath/testChildPathTwo]的数据 {}", new String(zk.getData("/testRootPath/testChildPathTwo", true, null)));

            // 删除目录节点
            zk.delete("/testRootPath/testChildPathTwo", -1);
            zk.delete("/testRootPath/testChildPathOne", -1);
            zk.delete("/testRootPath", -1);

            sleep(5000);
            // 关闭连接
            zk.close();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
