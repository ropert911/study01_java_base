package com.study.res.file.context;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Discription:[ ]
 */
@Configuration // 表示一个配置信息提供类
public class Beans {
    // 定义一个bean
    @Bean(name = "car")
    public Car buildCar() {
        Car car = new Car();
        car.setBrand("奥迪");
        car.setMaxSpeed(200);
        return car;
    }
}
