package com.xq.study.curator.lock;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by sk-qianxiao on 2018/5/16.
 */
//可重入读写锁. 使用前要先那一个 /xqtest/locks
public class ReentrantReadWriteLockDemo {
    private static final String HOST_URL = "192.168.20.51:2181";
    private static final String PATH = "/xqtest/locks";

    private final InterProcessReadWriteLock lock;
    private final InterProcessMutex readLock;
    private final InterProcessMutex writeLock;
    private final FakeLimitedResource resource;
    private final String clientName;

    public ReentrantReadWriteLockDemo(CuratorFramework client, String lockPath, FakeLimitedResource resource, String clientName) {
        this.resource = resource;
        this.clientName = clientName;
        lock = new InterProcessReadWriteLock(client, lockPath);     //可重入读写锁
        readLock = lock.readLock();
        writeLock = lock.writeLock();
    }


    private static final int QTY = 5;
    private static final int REPETITIONS = QTY;

    public static void main(String[] args) throws Exception {
        final FakeLimitedResource resource = new FakeLimitedResource();
        ExecutorService service = Executors.newFixedThreadPool(QTY);

        try {
            for (int i = 0; i < QTY; ++i) {
                final int index = i;
                Callable<Void> task = new Callable<Void>() {
                    @Override
                    public Void call() throws Exception {
                        CuratorFramework client = CuratorFrameworkFactory.newClient(HOST_URL, new ExponentialBackoffRetry(1000, 3));
                        try {
                            client.start();

                            final ReentrantReadWriteLockDemo example = new ReentrantReadWriteLockDemo(client, PATH, resource, "Client " + index);
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
                service.submit(task);
            }
            service.shutdown();
            service.awaitTermination(10, TimeUnit.MINUTES);
        } finally {
//            CloseableUtils.closeQuietly(server);
        }
    }

    public void doWork(long time, TimeUnit unit) throws Exception {
        // 注意只能先得到写锁再得到读锁，不能反过来！！！
        if (!writeLock.acquire(time, unit)) {
            throw new IllegalStateException(clientName + " 不能得到写锁");
        }
        System.out.println(clientName + " 已得到写锁");

        if (!readLock.acquire(time, unit)) {
            throw new IllegalStateException(clientName + " 不能得到读锁");
        }
        System.out.println(clientName + " 已得到读锁");

        try {
            resource.use(); // 使用资源
            Thread.sleep(1000);
        } finally {
            System.out.println(clientName + " 释放读写锁");
            readLock.release();
            writeLock.release();
        }
    }
}