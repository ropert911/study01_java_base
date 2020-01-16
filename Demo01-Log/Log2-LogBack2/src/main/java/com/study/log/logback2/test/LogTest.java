package com.study.log.logback2.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogTest {
    private static final Logger logger = LoggerFactory.getLogger(LogTest.class);

    public static void printlog(){
        logger.trace("LogTest:trace");
        logger.debug("LogTest:debug");
        logger.info("LogTest: info");
        logger.warn("LogTest:warn");
        logger.error("LogTest:error");
    }
}
