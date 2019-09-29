package com.xq.study.model;

import static java.lang.Thread.sleep;

/**
 * @author sk-qianxiao
 */
public class Shop {
    /**
     * 这里模块到不同的商店请求要花比较长的时间
     *
     * @return
     */
    public double calculatePrice() {
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
