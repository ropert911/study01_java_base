package com.study.rest.consumer.bean.context;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author sk-qianxiao
 */
@Configuration
public class Beans {
    @Bean(name = "car")
    public Car buildCar() {
        Car car = new Car();
        car.setBrand("奥迪");
        car.setMaxSpeed(200);
        return car;
    }
}
