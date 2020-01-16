package com.study.spring.bean.conditional;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author sk-qianxiao
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        ListService bean = context.getBean(ListService.class);
        System.out.println(bean.showListCmd());
        context.close();
    }
}
