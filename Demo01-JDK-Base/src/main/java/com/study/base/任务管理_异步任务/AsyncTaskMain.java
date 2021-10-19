package com.study.base.任务管理_异步任务;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

/**
 * 异步任务是通过 aop功能执行的
 *
 * @author sk-qianxiao
 */
public class AsyncTaskMain {
    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TaskExcutorConfig.class);
        List<Future<String>> resuls = new ArrayList();

        AsyncTaskService asyncTaskService = context.getBean(AsyncTaskService.class);
        for (int i = 0; i < 10; ++i) {
            Future<String> result = asyncTaskService.excuteAsyncTask(i);
            resuls.add(result);
        }

        for (Future<String> result : resuls) {
            System.out.println(result.get());
        }

        context.close();
    }
}
