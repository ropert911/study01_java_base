package com.study.scheduled;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringUtilsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringUtilsApplication.class, args);
    }
}
