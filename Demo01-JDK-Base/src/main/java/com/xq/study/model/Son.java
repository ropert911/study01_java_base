package com.xq.study.model;

import com.xq.study.model.Family;

/**
 * Created by sk-qianxiao on 2019/4/19.
 */
public class Son extends Family {
    private Family a;
    private String sonName;

    public String getSonName() {
        return sonName;
    }

    public void setSonName(String sonName) {
        this.sonName = sonName;
    }

    public Family getA() {
        return a;
    }

    public void setA(Family a) {
        this.a = a;
    }

    @Override
    public String toString() {
        return "Son{" +
                "SayHelloResponse=" + a +
                ", sonName='" + sonName + '\'' +
                '}';
    }
}
