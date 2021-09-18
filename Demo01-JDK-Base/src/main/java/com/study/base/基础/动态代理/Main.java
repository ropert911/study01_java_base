package com.study.base.基础.动态代理;

/**
 * @author sk-qianxiao
 * @date 2019/12/25
 */
public class Main {
    public static void main(String[] args) {
        JavaDeveloper jack = new JavaDeveloper("Jack");
        Developer jdkProxy = JdkProxy.newProxyInstance(jack);
        jdkProxy.code();
        jdkProxy.debug();

        Developer cglibProxy = CglibProxy.newProxyInstance(JavaDeveloper.class);
        cglibProxy.code();
        cglibProxy.debug();
    }
}
