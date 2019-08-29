package com.rest.test.resttest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestTestApplication {
    private static Logger logger = LoggerFactory.getLogger(RestTestApplication.class);
    private static org.apache.log4j.Logger logger2 = org.apache.log4j.Logger.getLogger(RestTestApplication.class);

    public static void main(String[] args) {
//        SpringApplication.run(RestTestApplication.class, args);
        logger.error("aaaaaaaaaaaaaaa");
        logger2.error("bbbbbbbbbbbbbb");
    }

}
