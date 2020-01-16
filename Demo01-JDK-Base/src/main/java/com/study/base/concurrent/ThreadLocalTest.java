package com.study.base.concurrent;

/**
 * ThreadLocal是用来保存线程量的
 *
 * @author sk-qianxiao
 * @date 2020/1/15
 */
public class ThreadLocalTest {
    static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) throws Exception {
        Thread thread = new Thread(() -> {
            int i = 5;
            while (i-- > 0) {
                try {
                    Thread.sleep(2000);
                    System.out.println("在线程里:" + threadLocal.get());
                } catch (Exception e) {
                }
            }
        });
        thread.start();

        threadLocal.set("abc");
        System.out.println("同一个线程:" + threadLocal.get());

        Thread.sleep(3000);
        System.out.println("remove删除数据==========");
        threadLocal.remove();
        Thread.sleep(1000);

        System.out.println("同一个线程:" + threadLocal.get());

    }
}


