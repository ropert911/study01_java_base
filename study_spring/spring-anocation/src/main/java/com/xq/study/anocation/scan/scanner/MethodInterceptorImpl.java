package com.xq.study.anocation.scan.scanner;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by sk-qianxiao on 2017/10/24.
 */
public class MethodInterceptorImpl implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("===========MethodInterceptorImpl:intercept" + method.getName());
        return methodProxy.invokeSuper(o, objects);
    }
}