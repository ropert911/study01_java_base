package com.study.zk.curator.lock;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessSemaphoreV2;
import org.apache.curator.framework.recipes.locks.Lease;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

/**
 * Created by sk-qianxiao on 2018/5/16.
 */
//共享信号量
public class InterProcessSemaphoreDemo {
    private static final String HOST_URL = "192.168.40.80:2181";
    private static final String PATH = "/xqtest/locks";

    private static final int MAX_LEASE = 10;

    public static void main(String[] args) throws Exception {
        FakeLimitedResource resource = new FakeLimitedResource();   //要使用的资源

        CuratorFramework client = CuratorFrameworkFactory.newClient(HOST_URL, new ExponentialBackoffRetry(1000, 3));
        client.start();

        InterProcessSemaphoreV2 semaphore = new InterProcessSemaphoreV2(client, PATH, MAX_LEASE);   //获取信号量
        Collection<Lease> leases = semaphore.acquire(5);
        System.out.println("获取到 " + leases.size() + " 个信号量");
        Lease lease = semaphore.acquire();
        System.out.println("只获取到一个");

        resource.use();

        Collection<Lease> leases2 = semaphore.acquire(5, 10, TimeUnit.SECONDS);
        System.out.println("应该超时，acquire 返回 " + leases2);

        System.out.println("释放一个信号");
        semaphore.returnLease(lease);

        System.out.println("释放5个信号");
        semaphore.returnAll(leases);
    }
}
