package com.xq.curator.lock;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by sk-qianxiao on 2018/5/16.
 */
//可重入共享锁，同一个线程可以多次 acquire
public class InterProcessMutexDemo {
    private static final String HOST_URL = "192.168.40.80:2181";
    private static final String PATH = "/xqtest/locks";

    private InterProcessMutex lock;
    private final FakeLimitedResource resource;
    private final String clientName;

    public InterProcessMutexDemo(CuratorFramework client, String lockPath, FakeLimitedResource resource, String clientName) {
        this.resource = resource;
        this.clientName = clientName;
        this.lock = new InterProcessMutex(client, lockPath);    //可重入共享锁
    }



    private static final int QTY = 5;
    private static final int REPETITIONS = QTY * 10;


    public static void main(String[] args) throws Exception {
        final FakeLimitedResource resource = new FakeLimitedResource(); //要操作的资源

        ExecutorService executorService = Executors.newFixedThreadPool(QTY);
        try {
            for (int i = 0; i < QTY; ++i) {
                final int index = i;
                Callable<Void> task = new Callable<Void>() {
                    @Override
                    public Void call() throws Exception {
                        CuratorFramework client = CuratorFrameworkFactory.newClient(HOST_URL, new ExponentialBackoffRetry(1000, 3));
                        try {
                            client.start();

                            final InterProcessMutexDemo example = new InterProcessMutexDemo(client, PATH, resource, "Client " + index);
                            for (int j = 0; j < REPETITIONS; ++j) {
                                example.doWork(10, TimeUnit.SECONDS);
                            }
                        } catch (Throwable e) {
                            e.printStackTrace();
                        } finally {
                            CloseableUtils.closeQuietly(client);
                        }
                        return null;
                    }
                };
                executorService.submit(task);
            }
            executorService.shutdown();
            executorService.awaitTermination(10, TimeUnit.MINUTES);
        } finally {
//            CloseableUtils.closeQuietly(server);
        }
    }


    public void doWork(long time, TimeUnit unit) throws Exception {
        if (!lock.acquire(time, unit)) {        //获取锁
            throw new IllegalStateException(clientName + " 无法 acquire 锁");
        }
        try {
            System.out.println(clientName + " 获取锁");
            resource.use(); //access resource exclusively
        } finally {
            System.out.println(clientName + " 释放锁");
            lock.release(); // always release the lock in a finally block
        }
    }
}

