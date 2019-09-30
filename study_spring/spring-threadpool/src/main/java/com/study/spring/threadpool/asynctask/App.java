package com.study.spring.threadpool.asynctask;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author sk-qianxiao
 */
public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TaskExcutorConfig.class);

        AsyncTaskService asyncTaskService = context.getBean(AsyncTaskService.class);
        for (int i = 0; i < 10; ++i) {
            asyncTaskService.excuteAsyncTask(i);
            asyncTaskService.excuteAsyncTask2(i);
        }

        context.close();
    }
}
