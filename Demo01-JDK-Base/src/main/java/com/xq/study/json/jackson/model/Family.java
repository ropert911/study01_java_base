package com.xq.study.json.jackson.model;

/**
 * Created by sk-qianxiao on 2019/4/19.
 */
public class Family {
    private String fName;

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    @Override
    public String toString() {
        return "Family{" +
                "fName='" + fName + '\'' +
                '}';
    }
}
