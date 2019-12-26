package com.xq.study.dynamic_proxy;

import java.lang.reflect.Proxy;

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
