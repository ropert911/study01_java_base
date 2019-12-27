package com.study.log.log4j2.log3log4j2;

import com.study.log.log4j2.log3log4j2.test.LogTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

public class Log3Log4j2Application {
    private static final Logger logger = LoggerFactory.getLogger(Log3Log4j2Application.class);
    public static void main(String[] args) {
        SpringApplication.run(Log3Log4j2Application.class, args);
        printLog();
        LogTest.printlog();
    }
    public static void printLog(){
        logger.trace("main:trace");
        logger.debug("main:debug");
        logger.info("main: info");
        logger.warn("main:warn");
        logger.error("main:error");
    }
}
