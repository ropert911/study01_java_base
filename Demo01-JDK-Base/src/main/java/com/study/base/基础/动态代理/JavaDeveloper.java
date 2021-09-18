package com.study.base.基础.动态代理;

/**
 * @author sk-qianxiao
 * @date 2019/12/25
 */
public class JavaDeveloper implements Developer {
    private String name;

    public JavaDeveloper() {
    }

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
        System.out.println(name + " is coding java.");
    }

    @Override
    public void debug() {
        System.out.println(name + " is debug java.");
    }
}
