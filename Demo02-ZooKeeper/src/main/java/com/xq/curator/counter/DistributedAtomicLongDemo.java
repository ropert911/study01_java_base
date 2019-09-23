package com.xq.curator.counter;

import com.google.common.collect.Lists;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.atomic.AtomicValue;
import org.apache.curator.framework.recipes.atomic.DistributedAtomicLong;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryNTimes;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by sk-qianxiao on 2018/5/16.
 */

//long 计数器
public class DistributedAtomicLongDemo {
    private static final String HOST_URL = "192.168.40.80:2181";
    private static final String PATH = "/xqtest/counter";

    private static final int QTY = 5;

    public static void main(String[] args) throws IOException, Exception {
        List<DistributedAtomicLong> examples = Lists.newArrayList();

        CuratorFramework client = CuratorFrameworkFactory.newClient(HOST_URL, new ExponentialBackoffRetry(1000, 3));
        client.start();

        ExecutorService service = Executors.newFixedThreadPool(QTY);
        for (int i = 0; i < QTY; ++i) {
            final DistributedAtomicLong count = new DistributedAtomicLong(client, PATH, new RetryNTimes(10, 10));
            examples.add(count);

            Callable<Void> task = () -> {
                try {
                    AtomicValue<Long> value = count.increment();
                    System.out.println("成功: " + value.succeeded());
                    if (value.succeeded())
                        System.out.println("增加: 从 " + value.preValue() + " 改为 " + value.postValue());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            };
            service.submit(task);
        }

        service.shutdown();
        service.awaitTermination(10, TimeUnit.MINUTES);
        Thread.sleep(Integer.MAX_VALUE);
    }
}

