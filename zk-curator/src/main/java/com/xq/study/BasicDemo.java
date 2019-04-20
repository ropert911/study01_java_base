package com.xq.study;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class BasicDemo {
    private static final Logger LOGGER = LoggerFactory.getLogger(BasicDemo.class);

    private static final String HOST_URL = "192.168.40.80:2181";

    //client 创建1
    static CuratorFramework client = createClient();

     public static void main(String[] args) {
        try {
            client.start();

            node_create_delete();   //节点添加、删除
            data_get_update();      //数据获取、更新
            check_node_exist();     //判断获取是否存在
            get_sub_node();         //遍历所有子目录
            transaction();          //事务操作
            async_excute();         //异步执行任务

            client.close();
        } catch (Exception e) {
            e.printStackTrace();
            CloseableUtils.closeQuietly(client);
        }
    }

    static CuratorFramework createClient() {
        //client 创建1
//         CuratorFramework client =  CuratorFrameworkFactory.newClient(HOST_URL, new RetryOneTime(1));

        //client 创建2
//        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
//        CuratorFramework client = CuratorFrameworkFactory.newClient(
//                HOST_URL,
//                5000,           //会话超时时间，单位毫秒，默认60000ms
//                3000,       //连接创建超时时间，单位毫秒，默认60000ms
//                retryPolicy);                     //重试策略,内建有四种重试策略,也可以自行实现RetryPolicy接口

        //client 创建3 fluent风格
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(HOST_URL)
                .sessionTimeoutMs(5000)
                .connectionTimeoutMs(5000)
                .retryPolicy(retryPolicy)
                .namespace("xqtest")      //分配一个独立的命名空间
                .build();

        return client;
    }

    private static void async_excute() throws Exception {
        //异步接口
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL)
                .inBackground((curatorFramework, curatorEvent) -> {
                    switch (curatorEvent.getType()) {    //事件类型
                        case CREATE:        //对应 create()
                        {
                            //getResultCode() 响应码
//                        0	    OK，即调用成功
//                        -4	ConnectionLoss，即客户端与服务端断开连接
//                        -110	NodeExists，即节点已经存在
//                        -112	SessionExpired，即会话过期
                            LOGGER.error("CREATE 异步结果 {}", curatorEvent.getResultCode());
                            break;
                        }
                        case DELETE:       //对应 delete()
                        {
                            break;
                        }
                        case EXISTS:      //对应 checkExists()
                        {
                            break;
                        }
                        case GET_DATA:        //对应 getData()
                        {
                            break;
                        }
                        case SET_DATA:        //对应 setData()
                        {
                            break;
                        }
                        case CHILDREN:        //对应 getChildren()
                        {
                            break;
                        }
                        case SYNC:            //对应 sync(String,Object)
                        {
                            break;
                        }
                        case GET_ACL:        //对应 getACL()
                        {
                            break;
                        }
                        case SET_ACL:        //对应 setACL()
                        {
                            break;
                        }
                        case WATCHED:        //对应 Watcher(Watcher)
                        {
                            break;
                        }
                        case CLOSING:        //对应 close()
                        {
                            break;
                        }
                    }

                }, executorService)    //如果#inBackground()方法不指定executor，那么会默认使用Curator的EventThread去进行异步处理
                .forPath("/path");
        client.delete().forPath("/path");
        try {
            Thread.sleep(1000);

            executorService.shutdown();     //不再接收新的任务
            if (!executorService.awaitTermination(1000, TimeUnit.MILLISECONDS)) { //等待固定时间让剩余的任务完成
                executorService.shutdownNow();  //立即停止
            }
        } catch (InterruptedException e) {
            // awaitTermination方法被中断的时候也中止线程池中全部的线程的执行。
            executorService.shutdownNow();      //立即停止
        }
    }

    private static void transaction() throws Exception {
        //事务
        client.create().forPath("/path");
        client.inTransaction().check().forPath("/path")     //检查path存在
                .and()
                .create().withMode(CreateMode.EPHEMERAL).forPath("/path1", "PATH1 创建的数据".getBytes())
                .and()
                .create().withMode(CreateMode.EPHEMERAL).forPath("/path2", "PATH2 创建的数据".getBytes())
                .and()
                .commit();
        LOGGER.error("[/path1] 数据 {}", new String(client.getData().forPath("/path1")));
        LOGGER.error("[/path2] 数据 {}", new String(client.getData().forPath("/path2")));

        client.delete().deletingChildrenIfNeeded().forPath("/path");
        client.delete().deletingChildrenIfNeeded().forPath("/path1");
        client.delete().deletingChildrenIfNeeded().forPath("/path2");
    }

    private static void get_sub_node() throws Exception {
        client.create()
                .creatingParentContainersIfNeeded()             //自动递归创建父节点
                .withMode(CreateMode.EPHEMERAL)                 //指定创建模式（临时节点）
                .forPath("/ephemeral/node_ephemeral1", "ephemeral node 1".getBytes());     //初始化内容
        client.create()
                .creatingParentContainersIfNeeded()             //自动递归创建父节点
                .withMode(CreateMode.EPHEMERAL)                 //指定创建模式（临时节点）
                .forPath("/ephemeral/node_ephemeral2", "ephemeral node 2".getBytes());     //初始化内容
        LOGGER.error("[/] 所有子目录 " + client.getChildren().forPath("/"));
        LOGGER.error("[/ephemeral] 所有子目录 " + client.getChildren().forPath("/ephemeral"));

        client.delete().deletingChildrenIfNeeded().forPath("/ephemeral");
    }

    private static void check_node_exist() throws Exception {
        client.create().forPath("/node_persistent1");                                         //默认为持久化节点_空节点
        LOGGER.error("[/path] 是否存在 {}", null != client.checkExists().forPath("/path"));
        LOGGER.error("[/node_persistent1] 是否存在 {}", null != client.checkExists().forPath("/node_persistent1"));
        client.delete().forPath("/node_persistent1");
    }

    private static void data_get_update() throws Exception {
        client.create().withMode(CreateMode.PERSISTENT).forPath("/node_persistent1", "persistent content 1".getBytes());

        String content = new String(client.getData().forPath("/node_persistent1"));
        LOGGER.info("获取到的内容  [{}]", content);

        Stat stat = new Stat();
        content = new String(client.getData().storingStatIn(stat).forPath("/node_persistent1"));       //读取一个节点的数据内容，同时获取到该节点的stat
        LOGGER.info("获取到的内容  [{}] Stat [{}]", content, stat);

        client.setData().forPath("/node_persistent1", "new data".getBytes());
        content = new String(client.getData().forPath("/node_persistent1"));
        LOGGER.info("获取更新后的内容  [{}]", content);

        client.delete().forPath("/node_persistent1");
    }

    private static void node_create_delete() throws Exception {
        //创建持久节点
        client.create().forPath("/node_persistent1");                                         //默认为持久化节点_空节点
        client.create().forPath("/node_persistent2", "persistent content 2".getBytes());      //创建一个节点，附带初始化内容
        client.create().withMode(CreateMode.PERSISTENT).forPath("/node_persistent3", "persistent content 3".getBytes());
        client.create().withMode(CreateMode.PERSISTENT).forPath("/node_persistent4", "persistent content 4".getBytes());
        client.setData().forPath("/node_persistent4", "persistent content 4-1".getBytes()).setVersion(1);
        client.create().withMode(CreateMode.PERSISTENT).forPath("/node_persistent5", "persistent content 5".getBytes());
        client.delete().forPath("/node_persistent1");
        client.delete().forPath("/node_persistent2");
        client.delete().forPath("/node_persistent3");
        client.delete().withVersion(1).forPath("/node_persistent4");    //删除指定版本
        client.delete().guaranteed().forPath("/node_persistent5");      //强制保证删除

        //持久序列化节点
        String path = client.create().withMode(CreateMode.PERSISTENT_SEQUENTIAL).forPath("/node_persistent_seq", "persistent_seq content".getBytes());
        client.delete().forPath(path);

        //创建临时节点
        client.create().withMode(CreateMode.EPHEMERAL).forPath("/node_ephemeral1");                             //创建一个节点，指定创建模式（临时节点），内容为空
        client.create().withMode(CreateMode.EPHEMERAL).forPath("/node_ephemeral2", "ephemeral node 2".getBytes());       //创建一个节点，指定创建模式（临时节点），附带初始化内容
        client.create()
                .creatingParentContainersIfNeeded()             //自动递归创建父节点
                .withMode(CreateMode.EPHEMERAL)                 //指定创建模式（临时节点）
                .forPath("/ephemeral/node_ephemeral3", "ephemeral node 3".getBytes());     //初始化内容
        client.delete().deletingChildrenIfNeeded().forPath("/ephemeral");

        //创建临时自增节点
        client.create().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath("/node_ephemeral_seq");
    }


}
