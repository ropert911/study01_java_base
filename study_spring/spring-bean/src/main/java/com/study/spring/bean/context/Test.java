package com.study.spring.bean.context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author sk-qianxiao
 */
public class Test {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Beans.class);
        Car car = applicationContext.getBean("car", Car.class);
        car.introduce();
    }
}
