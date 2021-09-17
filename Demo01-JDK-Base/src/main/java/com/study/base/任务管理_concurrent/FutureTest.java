package com.study.base.任务管理_concurrent;

import com.study.base.任务管理_concurrent.model.Shop;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;


import static java.util.stream.Collectors.toList;

/**
 * 异步请求示例
 *
 * @author sk-qianxiao
 */
public class FutureTest {
    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
    }

    public static void test1() {
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<Double> future = executor.submit(() -> {
            System.out.println("in thread");
            return 1d;
        });

        try {
            double result = future.get(1, TimeUnit.SECONDS);
            System.out.println(result);
        } catch (ExecutionException ee) {
            /** 计算抛出一个异常*/
        } catch (InterruptedException ie) {
            /** 当前线程在等待过程中被中断*/
        } catch (TimeoutException te) {
            /** 在Future对象完成之前超过已过期*/
        }

        executor.shutdown();
    }

    public static void test2() {
        long start = System.nanoTime();
        Shop s = new Shop("Shop-1");
        Future<Double> futurePrice1 = getPriceAsync1(s);
        // 执行更多任务，比如查询其他商店
        Future<Double> futurePrice2 = getPriceAsync2(s);

        try {
            double price1 = futurePrice1.get();
            double price2 = futurePrice2.get();
            System.out.println(price1);
            System.out.println(price2);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Price returned after " + retrievalTime + " msecs");
    }

    /**
     * 并发查询不同商店价格，使用CompletableFuture提供的异步机制
     */
    public static void test3() {
        long start = System.nanoTime();
        Shop s1 = new Shop("SHOP-1");
        Shop s2 = new Shop("SHOP-2");
        Shop s3 = new Shop("SHOP-3");
        Shop s4 = new Shop("SHOP-4");
        Shop s5 = new Shop("SHOP-5");
        Shop s6 = new Shop("SHOP-6");
        List<Shop> shops = new ArrayList<>();
        shops.add(s1);
        shops.add(s2);
        shops.add(s3);
        shops.add(s4);
        shops.add(s5);
        shops.add(s6);


        List<CompletableFuture<String>> priceFutures =
                shops.stream()
                        .map(shop -> CompletableFuture.supplyAsync(
                                () -> String.format("%s price is %.2f", shop.getName(), shop.calculatePrice())))
                        .collect(toList());

        /**等待所有的调用结束*/
        List<String> pList = priceFutures.stream().map(CompletableFuture::join).collect(toList());

        try {
            pList.forEach(s -> System.out.println(s));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Price returned after " + retrievalTime + " msecs");
    }

    /**
     * 并发请求多个，添加线程池提高并发度
     */
    public static void test4() {
        long start = System.nanoTime();

        Shop s1 = new Shop("SHOP-1");
        Shop s2 = new Shop("SHOP-2");
        Shop s3 = new Shop("SHOP-3");
        Shop s4 = new Shop("SHOP-4");
        Shop s5 = new Shop("SHOP-5");
        Shop s6 = new Shop("SHOP-6");
        List<Shop> shops = new ArrayList<>();
        shops.add(s1);
        shops.add(s2);
        shops.add(s3);
        shops.add(s4);
        shops.add(s5);
        shops.add(s6);

        ThreadFactory threadFactory = new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setDaemon(true);
                return t;
            }
        };
        /**也可以不指定threadFactory对象，使用默认的*/
        Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(), 100), threadFactory);

        List<CompletableFuture<String>> priceFutures =
                shops.stream().map(shop -> CompletableFuture.supplyAsync(() -> String.format("%s price is %.2f", shop.getName(), shop.calculatePrice()), executor)).collect(toList());

        List<String> pList = priceFutures.stream().map(CompletableFuture::join).collect(toList());

        try {
            pList.forEach(s -> System.out.println(s));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Price returned after " + retrievalTime + " msecs");
    }

    /**
     * 提供 CompletableFuture对象，自己提供线程为CompletableFuture提供结果
     *
     * @param s
     * @return
     */
    public static Future<Double> getPriceAsync1(Shop s) {
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread(() -> {
            try {
                double price = s.calculatePrice();
                futurePrice.complete(price);
            } catch (Exception ex) {
                futurePrice.completeExceptionally(ex);
            }
        }).start();
        //无需等待还没结束的计算，直接返回Future对象
        return futurePrice;
    }

    /**
     * 使用工厂方法supplyAsync创建CompletableFuture对象
     */
    public static Future<Double> getPriceAsync2(Shop s) {
        return CompletableFuture.supplyAsync(() -> s.calculatePrice());
    }
}
