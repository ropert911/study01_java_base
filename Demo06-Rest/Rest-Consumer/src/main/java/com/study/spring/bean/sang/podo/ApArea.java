package com.study.spring.bean.sang.podo;

/**
 * Created by sk-qianxiao on 2018/8/7.
 */
public class ApArea {
    private long topId;
    private String mac;

    public long getTopId() {
        return topId;
    }

    public void setTopId(long topId) {
        this.topId = topId;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    @Override
    public String toString() {
        return "ApArea{" +
                "topId=" + topId +
                ", mac='" + mac + '\'' +
                '}';
    }
}