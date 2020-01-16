package com.study.rest.consumer.bean.aware;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author sk-qianxiao
 * Created by sang on 16-12-14.
 */
@Service
@PropertySource(value = "t.properties", encoding = "UTF-8")
public class AwareService implements BeanNameAware, BeanFactoryAware, ResourceLoaderAware, EnvironmentAware {
    private String beanName;
    private ResourceLoader loader;
    private Environment environment;

    /**
     * 要建的Bean的名称
     * @param s
     */
    @Override
    public void setBeanName(String s) {
        System.out.println("BeanNameAware::setBeanName");
        this.beanName = s;
    }

    /**
     * 对BeanFactory进行测试
     * @param beanFactory
     * @throws BeansException
     */
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        //获取Bean的生成工厂
        System.out.println("BeanFactoryAware::setBeanFactory");
    }

    /**
     * 设备环境变量
     * @param environment
     */
    @Override
    public void setEnvironment(Environment environment) {
        System.out.println("EnvironmentAware::setEnvironment");
        this.environment = environment;
    }

    /**
     * 资源加载器
     * @param resourceLoader
     */
    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        System.out.println("ResourceLoaderAware::setResourceLoader");
        this.loader = resourceLoader;
    }

    public void output() {
        System.out.println("Bean的名称为：" + beanName);
        Resource resource = loader.getResource("t.txt");
        try {
            System.out.println(IOUtils.toString(resource.getInputStream(), "UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(environment.getProperty("sang.username"));
    }
}
