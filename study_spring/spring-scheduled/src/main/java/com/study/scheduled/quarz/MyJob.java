package com.study.scheduled.quarz;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

/**
 * @author xiaoqian
 *         Created by xq on 2017/10/19.
 */
public class MyJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        String name = jobDataMap.getString("name");
        System.out.println("测试Quartz : " + new Date() + " " + name);
    }
}
