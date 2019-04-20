package com.xq.study.lock;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessMultiLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.recipes.locks.InterProcessSemaphoreMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * Created by sk-qianxiao on 2018/5/16.
 */
//多共享锁对象
public class MultiSharedLockDemo {
    private static final String HOST_URL = "192.168.40.80:2181";
    private static final String PATH1 = "/xqtest/locks1";
    private static final String PATH2 = "/xqtest/locks2";

    public static void main(String[] args) throws Exception {
        FakeLimitedResource resource = new FakeLimitedResource();

        CuratorFramework client = CuratorFrameworkFactory.newClient(HOST_URL, new ExponentialBackoffRetry(1000, 3));
        client.start();

        InterProcessLock lock1 = new InterProcessMutex(client, PATH1);          //可重入共享锁
        InterProcessLock lock2 = new InterProcessSemaphoreMutex(client, PATH2); //不可重入共享锁
        InterProcessMultiLock lock = new InterProcessMultiLock(Arrays.asList(lock1, lock2));    //把这些共享锁都在多共享锁对象里

        if (!lock.acquire(10, TimeUnit.SECONDS)) {
            throw new IllegalStateException("无法获取到锁");
        }
        System.out.println("获取到锁");

        System.out.println("获取到锁1: " + lock1.isAcquiredInThisProcess());
        System.out.println("获取到锁2: " + lock2.isAcquiredInThisProcess());

        try {
            resource.use(); //access resource exclusively
        } finally {
            System.out.println("释放锁");
            lock.release(); // always release the lock in a finally block
        }

        System.out.println("获取到锁1: " + lock1.isAcquiredInThisProcess());
        System.out.println("获取到锁2: " + lock2.isAcquiredInThisProcess());
    }
}

