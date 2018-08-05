package com.xq.study.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//SLF4j 简单日志门面（Simple Logging Facade for Java）
//SLF4J是一个用于日志系统的简单Facade，允许最终用户在部署其应用时使用其所希望的日志系统
public class DemoLogApplication {
    private static final Logger logger = LoggerFactory.getLogger(DemoLogApplication.class);

    public static void main(String[] args) {
        logger.error("=======error msg");
        logger.warn("=======warn msg");
        logger.info("=======info msg");
        logger.debug("=======debug msg");
    }
}
