package com.xq.curator.cache;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * Created by sk-qianxiao on 2018/5/16.
 */
public class TreeCacheDemo {
    private static final String PATH = "/xqtest/cache";
    private static final String HOST_URL = "192.168.40.80:2181";

    public static void main(String[] args) throws Exception {
        CuratorFramework client = CuratorFrameworkFactory.newClient(HOST_URL, new ExponentialBackoffRetry(1000, 3));
        client.start();

        client.create().creatingParentsIfNeeded().forPath(PATH);

        TreeCache cache = new TreeCache(client, PATH);  //设置节点的cache
        TreeCacheListener listener = (client1, event) -> {
            //事件说明
            //NODE_ADDED  添加节点
            //NODE_UPDATED  节点修改
            //NODE_REMOVED  节点删除
            //CONNECTION_SUSPENDED,     //连接改为 state.ConnectionState#SUSPENDED 状态
            //CONNECTION_RECONNECTED    //连接改为 state.ConnectionState#RECONNECTED 重连状态
            //CONNECTION_LOST       //连接改为 state.ConnectionState#LOST 连接丢失
            //INITIALIZED   //cache 初始化完毕
            System.out.println("事件类型：" + event.getType() +
                    " | " + (null != event.getData() ? event.getData().getPath() + "=" + new String(event.getData().getData()) : null));
        };

        cache.getListenable().addListener(listener);
        cache.start();

        client.setData().forPath(PATH, "01".getBytes());
        Thread.sleep(100);
        client.setData().forPath(PATH, "02".getBytes());
        Thread.sleep(100);
        client.delete().deletingChildrenIfNeeded().forPath(PATH);
        Thread.sleep(1000 * 2);

        cache.close();
        client.close();
        System.out.println("OK!");
    }
}
