package com.study.base.任务管理_调度;

import com.study.base.任务管理_调度.elasticjob.config.RegistryCenterConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

/**
 * @author sk-qianxiao
 */

@EnableScheduling
@Component
public class Scheduled6Elasticjob {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RegistryCenterConfig.class);
//        context.close();
    }
}
