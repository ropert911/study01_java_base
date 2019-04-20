package com.xq.study.queue;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.queue.DistributedDelayQueue;
import org.apache.curator.framework.recipes.queue.QueueBuilder;
import org.apache.curator.framework.recipes.queue.QueueConsumer;
import org.apache.curator.framework.recipes.queue.QueueSerializer;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;

import java.util.Date;

/**
 * Created by sk-qianxiao on 2018/5/16.
 */
//分布式延迟队列
public class DistributedDelayQueueDemo {
    private static final String HOST_URL = "192.168.40.80:2181";
    private static final String PATH = "/xqtest/queue";

    public static void main(String[] args) throws Exception {
        CuratorFramework client = null;
        DistributedDelayQueue<String> queue = null;
        try {
            client = CuratorFrameworkFactory.newClient(HOST_URL, new ExponentialBackoffRetry(1000, 3));
            client.getCuratorListenable().addListener((client1, event) -> System.out.println("CuratorEvent: " + event.getType().name()));
            client.start();

            QueueConsumer<String> consumer = createQueueConsumer();
            QueueBuilder<String> builder = QueueBuilder.builder(client, consumer, createQueueSerializer(), PATH);
            queue = builder.buildDelayQueue();      //创建分布式延迟队列
            queue.start();

            for (int i = 0; i < 10; i++) {
                queue.put("test-" + i, System.currentTimeMillis() + 10000);
            }
            System.out.println(new Date().getTime() + ": 已放完全部消息");


            Thread.sleep(20000);

        } catch (Exception ex) {

        } finally {
            CloseableUtils.closeQuietly(queue);
            CloseableUtils.closeQuietly(client);
//            CloseableUtils.closeQuietly(server);
        }
    }

    private static QueueSerializer<String> createQueueSerializer() {
        return new QueueSerializer<String>() {
            @Override
            public byte[] serialize(String item) {
                return item.getBytes();
            }
            @Override
            public String deserialize(byte[] bytes) {
                return new String(bytes);
            }
        };
    }

    private static QueueConsumer<String> createQueueConsumer() {
        return new QueueConsumer<String>() {
            @Override
            public void stateChanged(CuratorFramework client, ConnectionState newState) {
                System.out.println("连接的新状态: " + newState.name());
            }
            @Override
            public void consumeMessage(String message) throws Exception {
                System.out.println(new Date().getTime() + ": 消费了一个消息: " + message);
            }
        };
    }
}
