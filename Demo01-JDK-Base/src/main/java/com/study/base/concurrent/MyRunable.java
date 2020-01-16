package com.study.base.concurrent;

/**
 * @author sk-qianxiao
 * @date 2020/1/15
 */
public class MyRunable implements Runnable{
    public static int count=20;
    public void run() {
        while(count>0) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"-[MyRunable]当前剩余票数:"+count--);
        }
    }
    public static void main(String[] args) {
        MyRunable myRunable= new MyRunable();
        Thread mThread1 = new Thread(myRunable, "线程1");
        Thread mThread2 = new Thread(myRunable, "线程2");
        Thread mThread3 = new Thread(myRunable, "线程3");
        mThread1.start();
        mThread2.start();
        mThread3.start();
    }
}
