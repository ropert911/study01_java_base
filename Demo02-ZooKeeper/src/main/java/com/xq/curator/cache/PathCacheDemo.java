package com.xq.curator.cache;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * Created by sk-qianxiao on 2018/5/16.
 */
// 路径缓存与监听
public class PathCacheDemo {

    private static final String PATH = "/xqtest/pathCache";
    private static final String HOST_URL = "192.168.40.80:2181";

    public static void main(String[] args) throws Exception {
        CuratorFramework client = CuratorFrameworkFactory.newClient(HOST_URL, new ExponentialBackoffRetry(1000, 3));
        client.start();

        //缓存节点数据
        PathChildrenCache cache = new PathChildrenCache(client, PATH, true);
        // NORMAL：正常初始化。
        // BUILD_INITIAL_CACHE：在调用start()之前会调用rebuild()。
        // POST_INITIALIZED_EVENT： 当Cache初始化数据后发送一个PathChildrenCacheEvent.Type#INITIALIZED事件
//        cache.start(POST_INITIALIZED_EVENT);
        cache.start();

        //添加监听
        PathChildrenCacheListener cacheListener = (client1, event) -> {
            System.out.println("事件类型：" + event.getType());
            if (null != event.getData()) {
                System.out.println("节点路径：" + event.getData().getPath() + " = " + new String(event.getData().getData()));
            }
        };
        cache.getListenable().addListener(cacheListener);

        client.create().creatingParentsIfNeeded().forPath("/xqtest/pathCache/test01", "01".getBytes());     //CHILD_ADDED 事件
//        Thread.sleep(10);
        client.create().creatingParentsIfNeeded().forPath("/xqtest/pathCache/test02", "02".getBytes());     //CHILD_ADDED 事件
        Thread.sleep(20);
        client.setData().forPath("/xqtest/pathCache/test01", "01_V2".getBytes());       //CHILD_UPDATED 事件
        Thread.sleep(10);

        //遍历子节点
        for (ChildData data : cache.getCurrentData()) {
            System.out.println("子节点信息:" + data.getPath() + " = " + new String(data.getData()));
        }

        client.delete().forPath("/xqtest/pathCache/test01");                //CHILD_REMOVED 事件
        Thread.sleep(10);
        client.delete().forPath("/xqtest/pathCache/test02");                //CHILD_REMOVED 事件
        client.delete().deletingChildrenIfNeeded().forPath("/xqtest/pathCache");
        Thread.sleep(1000 * 5);


        cache.close();
        client.close();
        System.out.println("OK!");
    }
}
