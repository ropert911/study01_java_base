package com.xq.curator.leader;

import com.google.common.collect.Lists;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by sk-qianxiao on 2018/5/16.
 */
public class LeaderSelectorDemo {
    protected static String PATH = "/xqtest/leader";
    private static final String HOST_URL = "192.168.40.80:2181";
    private static final int CLIENT_QTY = 10;

    public static void main(String[] args) throws Exception {
        List<CuratorFramework> clients = Lists.newArrayList();
        List<LeaderSelectorAdapter> examples = Lists.newArrayList();

        try {
            for (int i = 0; i < CLIENT_QTY; i++) {
                CuratorFramework client = CuratorFrameworkFactory.newClient(HOST_URL, new ExponentialBackoffRetry(20000, 3));
                clients.add(client);

                LeaderSelectorAdapter selectorAdapter = new LeaderSelectorAdapter(client, PATH, "Client #" + i);
                examples.add(selectorAdapter);

                client.start();
                selectorAdapter.start();
            }
            System.out.println("输入 enter/return to quit\n");
            new BufferedReader(new InputStreamReader(System.in)).readLine();
        } finally {
            System.out.println("关闭...");
            for (LeaderSelectorAdapter exampleClient : examples) {
                CloseableUtils.closeQuietly(exampleClient);
            }
            for (CuratorFramework client : clients) {
                CloseableUtils.closeQuietly(client);
            }
        }
    }
}