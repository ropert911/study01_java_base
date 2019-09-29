package com.xq.study.anocation.scan.scanner;

import com.xq.study.anocation.scan.ScanClass1;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.cglib.core.SpringNamingPolicy;
import org.springframework.cglib.proxy.Enhancer;

/**
 * Created by sk-qianxiao on 2017/10/24.
 */

public class FactoryBeanTest<T> implements InitializingBean, FactoryBean<T> {
    private String innerClassName;


    public void setInnerClassName(String innerClassName) {
        this.innerClassName = innerClassName;
    }

    @Override
    public T getObject() throws Exception {
//        Class innerClass = Class.forName(innerClassName);
        Class innerClass = ScanClass1.class;
        if (innerClass.isInterface()) {
            return (T) InterfaceProxy.newInstance(innerClass);
        } else {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(innerClass);
            enhancer.setNamingPolicy(SpringNamingPolicy.INSTANCE);
            enhancer.setCallback(new MethodInterceptorImpl());
            return (T) enhancer.create();
        }
    }

    @Override
    public Class<?> getObjectType() {
        return ScanClass1.class;
//        try {
//            return Class.forName(innerClassName);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return null;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
    }
}
