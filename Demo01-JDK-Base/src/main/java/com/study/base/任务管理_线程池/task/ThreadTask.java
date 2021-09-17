package com.study.base.任务管理_线程池.task;

public class ThreadTask extends Thread {

    private String name;

    public ThreadTask(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("获取" + name + "的信息并保存");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}