package com.xq.curator.counter;

import com.google.common.collect.Lists;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.shared.SharedCount;
import org.apache.curator.framework.recipes.shared.SharedCountListener;
import org.apache.curator.framework.recipes.shared.SharedCountReader;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by sk-qianxiao on 2018/5/16.
 */
//计数器
public class SharedCounterDemo implements SharedCountListener {
    private static final String HOST_URL = "192.168.40.80:2181";
    private static final String PATH = "/xqtest/counter";

    private static final int QTY = 5;

    public static void main(String[] args) throws IOException, Exception {
        final Random rand = new Random();
        SharedCounterDemo example = new SharedCounterDemo();

        CuratorFramework client = CuratorFrameworkFactory.newClient(HOST_URL, new ExponentialBackoffRetry(1000, 3));
        client.start();

        SharedCount baseCount = new SharedCount(client, PATH, 0);       //计数器
        baseCount.addListener(example);
        baseCount.start();

        List<SharedCount> examples = Lists.newArrayList();
        ExecutorService service = Executors.newFixedThreadPool(QTY);
        for (int i = 0; i < QTY; ++i) {
            final SharedCount count = new SharedCount(client, PATH, 0);
            examples.add(count);

            Callable<Void> task = () -> {
                count.start();

                Thread.sleep(rand.nextInt(10000));
                int num = rand.nextInt(10);
                System.out.println("增加:" + num + " 结果:"
                        + count.trySetCount(count.getVersionedValue(), count.getCount() + num));
                return null;
            };

            service.submit(task);
        }
        service.shutdown();
        service.awaitTermination(10, TimeUnit.MINUTES);

        for (int i = 0; i < QTY; ++i) {
            examples.get(i).close();
        }
        baseCount.close();
        Thread.sleep(Integer.MAX_VALUE);
    }

    @Override
    public void stateChanged(CuratorFramework arg0, ConnectionState arg1) {
        System.out.println("监听=》状态变更：" + arg1.toString());
    }

    @Override
    public void countHasChanged(SharedCountReader sharedCount, int newCount) throws Exception {
        System.out.println("监听=》计数器变更： " + newCount);
    }
}

