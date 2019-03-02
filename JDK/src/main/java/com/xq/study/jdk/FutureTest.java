package com.xq.study.jdk;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static java.lang.Thread.sleep;
import static java.util.stream.Collectors.toList;

/**
 * Created by xq on 2019/3/2.
 */
public class FutureTest {
    public static void main(String[] args) {
//        test1();
//        System.out.println("=================");
//        test2();    //使用 CompletableFuture，还有工厂类
        System.out.println("=================");
        test3();     //并发查询不同商店价格
        System.out.println("=================");
        test4();     //添加线程池提高并发度
    }

    public static void test4() {
        long start = System.nanoTime();
        Shop s1 = new Shop("SHOP-1");
        Shop s2 = new Shop("SHOP-1");
        Shop s3 = new Shop("SHOP-3");
        Shop s4 = new Shop("SHOP-2");
        Shop s5 = new Shop("SHOP-4");
        Shop s6 = new Shop("SHOP-5");
        List<Shop> shops = new ArrayList<>();
        shops.add(s1);
        shops.add(s2);
        shops.add(s3);
        shops.add(s4);
        shops.add(s5);
        shops.add(s6);
        shops.add(s1);
        shops.add(s2);
        shops.add(s3);
        shops.add(s4);
        shops.add(s5);
        shops.add(s6);
        Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(), 100),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread t = new Thread(r);
                        t.setDaemon(true);
                        return t;
                    }
                });

        List<CompletableFuture<String>> priceFutures =
                shops.stream()
                        .map(shop -> CompletableFuture.supplyAsync(() -> String.format("%s price is %.2f", shop.getName(), shop.getPrice()),
                                executor))
                        .collect(toList());

        List<String> pList = priceFutures.stream().map(CompletableFuture::join).collect(toList());

        try {
            pList.forEach(s -> System.out.println(s));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Price returned after " + retrievalTime + " msecs");
    }

    public static void test3() {
        long start = System.nanoTime();
        Shop s1 = new Shop("SHOP-1");
        Shop s2 = new Shop("SHOP-1");
        Shop s3 = new Shop("SHOP-3");
        Shop s4 = new Shop("SHOP-2");
        Shop s5 = new Shop("SHOP-4");
        Shop s6 = new Shop("SHOP-5");
        List<Shop> shops = new ArrayList<>();
        shops.add(s1);
        shops.add(s2);
        shops.add(s3);
        shops.add(s4);
        shops.add(s5);
        shops.add(s6);
        shops.add(s1);
        shops.add(s2);
        shops.add(s3);
        shops.add(s4);
        shops.add(s5);
        shops.add(s6);


        List<CompletableFuture<String>> priceFutures =
                shops.stream()
                        .map(shop -> CompletableFuture.supplyAsync(
                                () -> String.format("%s price is %.2f",
                                        shop.getName(), shop.getPrice())))
                        .collect(toList());

        List<String> pList = priceFutures.stream().map(CompletableFuture::join).collect(toList());

        try {
            pList.forEach(s -> System.out.println(s));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Price returned after " + retrievalTime + " msecs");
    }

    public static void test2() {
        long start = System.nanoTime();
        Shop s = new Shop("Shop-1");
        Future<Double> futurePrice1 = s.getPriceAsync1();
        // 执行更多任务，比如查询其他商店
        Future<Double> futurePrice2 = s.getPriceAsync2();

        try {
            double price1 = futurePrice1.get();
            double price2 = futurePrice2.get();
            System.out.printf("Price is %.2f  %.2f%n", price1, price2);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Price returned after " + retrievalTime + " msecs");
    }


    static void test1() {
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<Double> future = executor.submit(() -> {
            System.out.println("in thread");
            return 1d;
        });

        try {
//            sleep(3);
            System.out.println("in main");
            Double result = future.get(1, TimeUnit.SECONDS);
            System.out.println("get" + result);
        } catch (ExecutionException ee) {
            // 计算抛出一个异常
        } catch (InterruptedException ie) {
            // 当前线程在等待过程中被中断
        } catch (TimeoutException te) {
            // 在Future对象完成之前超过已过期
        }

        executor.shutdown();
    }
}

class Shop {

    public Double getPrice() {
        return calculatePrice();
    }


    public Future<Double> getPriceAsync1() {
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread(() -> {
            try {
                double price = calculatePrice();
                futurePrice.complete(price);
            } catch (Exception ex) {
                futurePrice.completeExceptionally(ex);
            }
        }).start();
        //无需等待还没结束的计算，直接返回Future对象
        return futurePrice;
    }

    //使用工厂方法supplyAsync创建CompletableFuture对象
    public Future<Double> getPriceAsync2() {
        return CompletableFuture.supplyAsync(() -> calculatePrice());
    }

    private double calculatePrice() {
        try {
            sleep(1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return Math.random() * 100;
    }

    private String name;

    public Shop(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
