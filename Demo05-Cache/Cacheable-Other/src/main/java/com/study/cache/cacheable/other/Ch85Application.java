package com.study.cache.cacheable.other;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author sk-qianxiao
 */
@SpringBootApplication
@EnableCaching
public class Ch85Application {
    public static void main(String[] args) {
        SpringApplication.run(Ch85Application.class, args);
    }
}
