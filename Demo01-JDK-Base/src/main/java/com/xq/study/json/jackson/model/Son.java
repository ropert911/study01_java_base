package com.xq.study.json.jackson.model;

/**
 * @author sk-qianxiao
 */
public class Son extends Family {
    private Family family;
    private String sonName;

    public String getSonName() {
        return sonName;
    }

    public void setSonName(String sonName) {
        this.sonName = sonName;
    }

    public Family getFamily() {
        return family;
    }

    public void setFamily(Family family) {
        this.family = family;
    }

    @Override
    public String toString() {
        return "Son{" +
                "SayHelloResponse=" + family +
                ", sonName='" + sonName + '\'' +
                '}';
    }
}
