package com.study.base.基础.动态代理;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author sk-qianxiao
 * @date 2019/12/26
 */
public class CglibProxy implements MethodInterceptor {
    private CglibProxy() {

    }

    public static <T extends Developer> Developer newProxyInstance(Class<T> targetInstanceClazz) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(targetInstanceClazz);
        enhancer.setCallback(new CglibProxy());
        return (Developer) enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy proxy) throws Throwable {
        return proxy.invokeSuper(o, objects);
    }
}
