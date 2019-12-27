package com.study.log.logback.log1logback;

import com.study.log.logback.log1logback.test.LogTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

public class Log1LogbackApplication {
    private static final Logger logger = LoggerFactory.getLogger(Log1LogbackApplication.class);

    public static void main(String[] args) {
        //加了这个，日志的配置才会生效，默认是不生效的
        SpringApplication.run(Log1LogbackApplication.class, args);
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
