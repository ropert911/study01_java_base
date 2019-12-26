package com.xq.study.dynamic_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author sk-qianxiao
 * @date 2019/12/25
 */
public class JdkProxy implements InvocationHandler {
    private Developer obj;

    private JdkProxy(Developer obj) {
        this.obj = obj;
    }

    public static Developer newProxyInstance(Developer developer){
        return (Developer) Proxy.newProxyInstance(developer.getClass().getClassLoader(), developer.getClass().getInterfaces(), new JdkProxy(developer));
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
