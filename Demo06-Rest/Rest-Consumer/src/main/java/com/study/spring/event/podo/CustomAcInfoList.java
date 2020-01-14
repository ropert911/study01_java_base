package com.study.spring.event.podo;

import java.util.List;

/**
 * Created by sk-qianxiao on 2019/5/20.
 */
public class CustomAcInfoList {
    private List<CustomAcInfo> list;

    public List<CustomAcInfo> getList() {
        return list;
    }

    public void setList(List<CustomAcInfo> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "CustomAcInfoList{" +
                "list=" + list +
                '}';
    }
}