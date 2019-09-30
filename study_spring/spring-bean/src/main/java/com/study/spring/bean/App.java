package com.study.spring.bean;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author sk-qianxiao
 */
@SpringBootApplication
@EnableScheduling
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
