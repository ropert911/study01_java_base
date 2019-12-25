package com.xq.study.dynamic_proxy;

import java.lang.reflect.Proxy;

/**
 * @author sk-qianxiao
 * @date 2019/12/25
 */
public class Main {
    public static void main(String[] args) {
        JavaDeveloper jack = new JavaDeveloper("Jack");

        Developer jackProxy = (Developer) Proxy.newProxyInstance(jack.getClass().getClassLoader(), jack.getClass().getInterfaces(), new JackHandler(jack));
        jackProxy.code();
        jackProxy.debug();
    }
}
