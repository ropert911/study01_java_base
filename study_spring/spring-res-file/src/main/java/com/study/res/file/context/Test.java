package com.study.res.file.context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

/**
 * Discription:[ ]
 */
public class Test {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Beans.class);
        Car car = applicationContext.getBean("car", Car.class);
        car.introduce();
    }
}
