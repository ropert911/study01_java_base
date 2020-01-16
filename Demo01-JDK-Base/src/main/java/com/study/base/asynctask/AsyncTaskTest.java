package com.study.base.asynctask;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 异步任务是通过 aop功能执行的
 *
 * @author sk-qianxiao
 */
public class AsyncTaskTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TaskExcutorConfig.class);

        AsyncTaskService asyncTaskService = context.getBean(AsyncTaskService.class);
        for (int i = 0; i < 10; ++i) {
            System.out.println("主线程ID【" + i + "】的线程threadid=[" + Thread.currentThread().getId());
            asyncTaskService.excuteAsyncTask(i);
        }

        context.close();
    }
}
