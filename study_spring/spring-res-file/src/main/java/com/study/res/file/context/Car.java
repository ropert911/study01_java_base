package com.study.res.file.context;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;

/**
 * Discription:[ ]
 */
// 管理bean生命周期的接口 (bean级的生命周期控制接口)
public class Car implements BeanFactoryAware, BeanNameAware, InitializingBean, DisposableBean {
    private String brand;
    private String color;
    private int maxSpeed;
    private String name;
    private BeanFactory beanFactory;
    private String beanName;

    public Car() {
        System.out.println("调用Car()构造函数。");
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        System.out.println("调用setBrand()设置属性。");
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "brand:" + brand + "/color:" + color + "/maxSpeed:" + maxSpeed;
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

    public void introduce() {
        System.out.println("introduce:" + this.toString());
    }

    // BeanFactoryAware接口方法
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("第7步 - 将BeanFactory容器实例设置到bean中");
    }

    // BeanNameAware接口方法
    @Override
    public void setBeanName(String s) {
        System.out.println("第6步 - 将配置文件中的该bean对应的名称设置到bean中");
    }

    // InitializingBean接口方法
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("第9步 ");
    }

    // DisposableBean接口方法
    @Override
    public void destroy() throws Exception {
        System.out.println("第13步 - 释放资源,记录日志等");
    }

    public void myInit() {
        System.out.println("初始化 - 调用myInit()，将maxSpeed设置为240。");
        this.maxSpeed = 240;
    }

    public void myDestory() {
        System.out.println("销毁 - 调用myDestroy()。");
    }
}
