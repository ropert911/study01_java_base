package com.study.rest.consumer.aop;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author sk-qianxiao
 */
@SpringBootApplication
public class AopApplication implements ApplicationRunner {
    @Autowired
    Performance performance;

    public static void main(String[] args) {
        SpringApplication.run(AopApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        performance.performance();
        performance.performance(1);
    }
}
