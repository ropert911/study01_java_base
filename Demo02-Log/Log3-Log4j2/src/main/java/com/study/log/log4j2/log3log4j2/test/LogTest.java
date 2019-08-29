package com.study.log.log4j2.log3log4j2.test;

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
