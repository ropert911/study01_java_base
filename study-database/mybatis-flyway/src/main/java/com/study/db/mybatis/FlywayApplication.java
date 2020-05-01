package com.study.db.mybatis;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author sk-qianxiao
 */
@SpringBootApplication
@MapperScan("com.study.db.mybatis.flyway.repository")
public class FlywayApplication {
    public static void main(String[] args) {
        SpringApplication.run(FlywayApplication.class, args);
    }
}
