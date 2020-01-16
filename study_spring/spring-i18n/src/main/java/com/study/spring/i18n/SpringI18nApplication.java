package com.study.spring.i18n;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;

import javax.annotation.Resource;
import java.util.Locale;

/**
 * @author sk-qianxiao
 */
@SpringBootApplication
public class SpringI18nApplication implements ApplicationRunner {
    private final static Logger logger = LoggerFactory.getLogger(SpringI18nApplication.class);
    @Resource
    private MessageSource messageSource;

    public static void main(String[] args) {
        SpringApplication.run(SpringI18nApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        logger.info("===================={}", messageSource.getMessage("welcome", null, Locale.US));
        logger.info("===================={}", messageSource.getMessage("welcome", null, Locale.CHINA));
    }
}
