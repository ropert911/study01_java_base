package com.xq.study.queue;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.queue.DistributedPriorityQueue;
import org.apache.curator.framework.recipes.queue.QueueBuilder;
import org.apache.curator.framework.recipes.queue.QueueConsumer;
import org.apache.curator.framework.recipes.queue.QueueSerializer;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;

/**
 * Created by sk-qianxiao on 2018/5/16.
 */

//优先分布队列
public class DistributedPriorityQueueDemo {
    private static final String HOST_URL = "192.168.40.80:2181";
    private static final String PATH = "/xqtest/queue";

    public static void main(String[] args) throws Exception {
        CuratorFramework client = null;
        DistributedPriorityQueue<String> queue = null;
        try {
            client = CuratorFrameworkFactory.newClient(HOST_URL, new ExponentialBackoffRetry(1000, 3));
            client.getCuratorListenable().addListener((client1, event) -> System.out.println("CuratorEvent: " + event.getType().name()));
            client.start();

            QueueConsumer<String> consumer = createQueueConsumer();
            QueueBuilder<String> builder = QueueBuilder.builder(client, consumer, createQueueSerializer(), PATH);
            queue = builder.buildPriorityQueue(0);  //创建优先队列    设置你的程序可以容忍的不排序的最小值
            queue.start();

            for (int i = 0; i < 10; i++) {
                int priority = (int) (Math.random() * 100);
                System.out.println("test-" + i + " priority:" + priority);
                queue.put("test-" + i, priority);

                Thread.sleep((long) (50 * Math.random()));
            }

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
                Thread.sleep(1000);
                System.out.println("消费消息: " + message);
            }
        };
    }
}
