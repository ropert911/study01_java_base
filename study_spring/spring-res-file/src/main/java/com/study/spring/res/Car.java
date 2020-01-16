package com.study.spring.res;

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

    public Car() {
        System.out.println("Car()构造函数。");
    }

    public void introduce() {
        System.out.println("introduce:" + this.toString());
    }

    public String getBrand() {
        System.out.println("getBrand");
        return brand;
    }

    public void setBrand(String brand) {
        System.out.println("setBrand");
        this.brand = brand;
    }

    public String getColor() {
        System.out.println("getColor");
        return color;
    }

    public void setColor(String color) {
        System.out.println("setColor");
        this.color = color;
    }

    public int getMaxSpeed() {
        System.out.println("getMaxSpeed");
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        System.out.println("setMaxSpeed");
        this.maxSpeed = maxSpeed;
    }


    /**
     * BeanFactoryAware接口方法
     *
     * @param beanFactory
     * @throws BeansException
     */
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("setBeanFactory - 将BeanFactory容器实例设置到bean中");
    }

    /**
     * BeanNameAware接口方法
     *
     * @param s
     */
    @Override
    public void setBeanName(String s) {
        System.out.println("setBeanName - 将配置文件中的该bean对应的名称设置到bean中==" + s);
    }

    /**
     * InitializingBean接口方法
     *
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet ");
    }

    /**
     * DisposableBean接口方法
     *
     * @throws Exception
     */
    @Override
    public void destroy() throws Exception {
        System.out.println("destroy - 释放资源,记录日志等");
    }

    public void myInit(){

    }

    public void myDestory() throws Exception {
        System.out.println("myDestory - 释放资源,记录日志等");
    }
}
