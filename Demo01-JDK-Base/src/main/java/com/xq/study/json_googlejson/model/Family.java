package com.xq.study.json_googlejson.model;

/**
 * @author sk-qianxiao
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
