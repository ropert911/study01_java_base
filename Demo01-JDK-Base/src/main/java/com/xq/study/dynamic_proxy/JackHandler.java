package com.xq.study.dynamic_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author sk-qianxiao
 * @date 2019/12/25
 */
public class JackHandler implements InvocationHandler {
    private Object obj;

    public JackHandler(Object obj) {
        this.obj = obj;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals("code")) {
            System.out.println("Jack is praying for the code");
            return method.invoke(obj, args);
        }
        if (method.getName().equals("debug")) {
            System.out.println("Jack hava no bug");
            return null;
        }
        return null;
    }
}
