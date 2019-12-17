package com.xq.zookeeper;

import org.apache.zookeeper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static java.lang.Thread.sleep;

public class ZKDemoEPHEMERALNode {
    private static final Logger logger = LoggerFactory.getLogger(ZKDemoEPHEMERALNode.class);
    private static final int TIME_OUT = 3000;
    private static final String HOST = "192.168.20.148:2181,192.168.20.149:2181,192.168.20.150:2181";

    public static void main(String arg[]) {
        try {
            ZooKeeper zk = new ZooKeeper(HOST, TIME_OUT, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                }
            });

            boolean success = createEphemeralFlagNode(zk, "/bg_data_monitor_streamflag", "this is flag for stream task is running");
            logger.info("result============================= {}", success);
            sleep(60000);
            zk.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean createEphemeralFlagNode(ZooKeeper zk, String path, String data) {
        boolean isSuccess = false;
        try {
            //创建一个目录节点和子目录结点
            zk.create(path, data.getBytes(), ZooDefs.Ids.READ_ACL_UNSAFE, CreateMode.EPHEMERAL);
            isSuccess = true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }

        return isSuccess;
    }
}
