package com.study.log.log3.log3log4j2_2.test;


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
