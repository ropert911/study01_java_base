package com.study.zk.curator.queue;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.queue.SimpleDistributedQueue;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;

/**
 * Created by sk-qianxiao on 2018/5/16.
 */
//带一些常用函数的队列
public class SimpleDistributedQueueDemo {
    private static final String HOST_URL = "192.168.40.80:2181";
    private static final String PATH = "/xqtest/queue";

    public static void main(String[] args) throws Exception {
        CuratorFramework client = null;
        SimpleDistributedQueue queue;

        try {
            client = CuratorFrameworkFactory.newClient(HOST_URL, new ExponentialBackoffRetry(1000, 3));
            client.getCuratorListenable().addListener((client1, event) -> System.out.println("CuratorEvent: " + event.getType().name()));
            client.start();

            queue = new SimpleDistributedQueue(client, PATH);   //创建队列
            Producer producer = new Producer(queue);
            Consumer consumer = new Consumer(queue);

            new Thread(producer, "producer").start();
            new Thread(consumer, "consumer").start();
            Thread.sleep(20000);
        } catch (Exception ex) {

        } finally {
            CloseableUtils.closeQuietly(client);
//            CloseableUtils.closeQuietly(server);
        }
    }

    public static class Producer implements Runnable {
        private SimpleDistributedQueue queue;

        public Producer(SimpleDistributedQueue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    boolean flag = queue.offer(("zjc-" + i).getBytes());
                    if (flag) {
                        System.out.println("发送一条消息成功：" + "zjc-" + i);
                    } else {
                        System.out.println("发送一条消息失败：" + "zjc-" + i);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class Consumer implements Runnable {

        private SimpleDistributedQueue queue;

        public Consumer(SimpleDistributedQueue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < 5; i++) {
                    byte[] datas = queue.take();
                    System.out.println("消费一条消息成功：" + new String(datas, "UTF-8"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
