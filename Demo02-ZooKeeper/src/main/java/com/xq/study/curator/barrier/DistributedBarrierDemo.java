package com.xq.study.curator.barrier;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.barriers.DistributedBarrier;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by sk-qianxiao on 2018/5/16.
 */
//栅栏功能
public class DistributedBarrierDemo {
    protected static String PATH = "/xqtest/barrier";
    private static final String HOST_URL = "192.168.40.80:2181";

    private static final int QTY = 5;

    public static void main(String[] args) throws Exception {
        CuratorFramework client = CuratorFrameworkFactory.newClient(HOST_URL, new ExponentialBackoffRetry(1000, 3));
        client.start();

        ExecutorService service = Executors.newFixedThreadPool(QTY);
        DistributedBarrier controlBarrier = new DistributedBarrier(client, PATH);   //创建栅栏
        controlBarrier.setBarrier();

        for (int i = 0; i < QTY; ++i) {
            final DistributedBarrier barrier = new DistributedBarrier(client, PATH);    //创建栅栏
            final int index = i;
            Callable<Void> task = () -> {
                Thread.sleep((long) (3 * Math.random()));
                System.out.println("Client #" + index + " 等待栅栏打开");

                barrier.waitOnBarrier();    //等待栅栏

                System.out.println("Client #" + index + " 开始执行");
                return null;
            };
            service.submit(task);
        }
        Thread.sleep(10000);
        System.out.println("所以栅栏实例都应该等待这个条件");
        controlBarrier.removeBarrier();     //打开栅栏

        service.shutdown();
        service.awaitTermination(10, TimeUnit.MINUTES);

        Thread.sleep(20000);
    }
}
