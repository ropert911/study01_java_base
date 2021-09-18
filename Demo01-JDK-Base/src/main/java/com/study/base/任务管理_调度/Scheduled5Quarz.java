package com.study.base.任务管理_调度;

import com.study.base.任务管理_调度.quarzJob.MyQuarzJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.calendar.AnnualCalendar;

import java.util.GregorianCalendar;
import java.util.Calendar;

/**
 * @author xiaoqian
 * Created by xq on 2017/10/19.
 */
public class Scheduled5Quarz {
    public static void test() {
        try {
            //创建jobDetail实例，绑定Job实现类
            JobDetail jobDetail = JobBuilder.newJob(MyQuarzJob.class)
                    //任务名，用于引用该任务
                    .withIdentity("job2", "jgroup1")
                    //任务数据，可作任务的参数
                    .usingJobData("name", "孙悟空")
                    .build();

            //使用simpleTrigger规则触发器
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("simpleTrigger", "triggerGroup")
                    .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(2).withRepeatCount(36000))
                    .startNow()     //.startAt() 设定开始时间
                    .build();
            if (false) {
                //使用cornTrigger规则
                trigger = TriggerBuilder.newTrigger()
                        .withIdentity("cornTrigger", "cornGroup")
                        .withSchedule(CronScheduleBuilder.cronSchedule("3/1 * * * * ? *"))
                        .startNow()
                        .build();
            }

            //通过schedulerFactory获取一个调度器
            SchedulerFactory schedulerfactory = new StdSchedulerFactory();
            Scheduler scheduler = schedulerfactory.getScheduler();

            //设备任务触发规则
            scheduler.scheduleJob(jobDetail, trigger);

            //设备假期日历
            AnnualCalendar holidays = new AnnualCalendar();
            Calendar halloween = new GregorianCalendar(2005, 9, 31);
            holidays.setDayExcluded(halloween, true);
            scheduler.addCalendar("holidays", holidays, false, false);

            scheduler.start();
        } catch (
                Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        test();
    }
}