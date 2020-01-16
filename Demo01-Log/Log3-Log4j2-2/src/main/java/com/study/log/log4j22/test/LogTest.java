package com.study.log.log4j22.test;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogTest {
    private static Logger logger = LogManager.getLogger(LogTest.class);

    public static void printlog(){
        logger.trace("LogTest:trace");
        logger.debug("LogTest:debug");
        logger.info("LogTest: info");
        logger.warn("LogTest:warn");
        logger.error("LogTest:error");
    }
}
