package com.study.spring.bean.context;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;

/**
 * 管理bean生命周期的接口 (bean级的生命周期控制接口)
 *
 * @author sk-qianxiao
 */
public class Car implements BeanFactoryAware, BeanNameAware, InitializingBean, DisposableBean {
    private String brand;
    private String color;
    private int maxSpeed;

    /**
     * BeanFactoryAware接口方法
     *
     * @param beanFactory
     * @throws BeansException
     */
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("第7步 - 将BeanFactory容器实例设置到bean中");
    }

    /**
     * BeanNameAware接口方法
     *
     * @param s
     */
    @Override
    public void setBeanName(String s) {
        System.out.println("第6步 - 将配置文件中的该bean对应的名称设置到bean中");
    }

    /**
     * InitializingBean接口方法
     *
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("第9步 ");
    }

    public Car() {
        System.out.println("调用Car()构造函数。");
    }

    public void introduce() {
        System.out.println("introduce:" + this.toString());
    }

    /**
     * DisposableBean接口方法
     *
     * @throws Exception
     */
    @Override
    public void destroy() throws Exception {
        System.out.println("第13步 - 释放资源,记录日志等");
    }


    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }
}
