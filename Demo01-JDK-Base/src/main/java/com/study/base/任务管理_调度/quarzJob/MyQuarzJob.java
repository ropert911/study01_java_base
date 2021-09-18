package com.study.base.任务管理_调度.quarzJob;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

/**
 * @author xiaoqian
 * Created by xq on 2017/10/19.
 */
public class MyQuarzJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        String jobName = context.getJobDetail().getKey().getName();
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        String name = jobDataMap.getString("name");
        System.out.println("jobName：" + jobName + "   job data-name：" + name + " " + new Date());
    }
}
