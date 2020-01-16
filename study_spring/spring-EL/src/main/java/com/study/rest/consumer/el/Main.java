package com.study.rest.consumer.el;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author sk-qianxiao
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        ELConfig bean = context.getBean(ELConfig.class);
        bean.output();
        context.close();
    }
}
