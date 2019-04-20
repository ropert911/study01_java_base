package com.xq.study.curator.barrier;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.barriers.DistributedDoubleBarrier;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by sk-qianxiao on 2018/5/16.
 */

//双栅栏
public class DistributedDoubleBarrierDemo {
    protected static String PATH = "/xqtest/barrier";
    private static final String HOST_URL = "192.168.40.80:2181";

    private static final int QTY = 5;

    public static void main(String[] args) throws Exception {
        CuratorFramework client = CuratorFrameworkFactory.newClient(HOST_URL, new ExponentialBackoffRetry(1000, 3));
        client.start();

        ExecutorService service = Executors.newFixedThreadPool(QTY);
        for (int i = 0; i < QTY; ++i) {
            final DistributedDoubleBarrier barrier = new DistributedDoubleBarrier(client, PATH, QTY);

            final int index = i;
            Callable<Void> task = () -> {
                Thread.sleep((long) (3 * Math.random()));
                System.out.println("Client #" + index + " enters");

                barrier.enter();    //等待
                System.out.println("Client #" + index + " 开始");
                Thread.sleep((long) (3000 * Math.random()));

                barrier.leave();    //等待
                System.out.println("Client #" + index + " 完成");
                return null;
            };
            service.submit(task);
        }

        service.shutdown();
        service.awaitTermination(10, TimeUnit.MINUTES);
        Thread.sleep(Integer.MAX_VALUE);
    }
}
