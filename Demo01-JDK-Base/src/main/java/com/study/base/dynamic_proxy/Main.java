package com.study.base.dynamic_proxy;

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
