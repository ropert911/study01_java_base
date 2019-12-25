package com.xq.study.dynamic_proxy;

/**
 * @author sk-qianxiao
 * @date 2019/12/25
 */
public class JavaDeveloper implements Developer{
    private String name;

    public JavaDeveloper(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void code() {
        System.out.println(name+" is coding java.");
    }

    @Override
    public void debug() {
        System.out.println(name+" is debug java.");
    }
}
