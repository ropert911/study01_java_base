package com.study.rest.consumer.event;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author sk-qianxiao
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        DemoPublish demoPublish = context.getBean(DemoPublish.class);
        demoPublish.publish("Hello sang !");
        context.close();
    }
}
