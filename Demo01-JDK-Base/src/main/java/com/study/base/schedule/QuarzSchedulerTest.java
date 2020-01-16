package com.study.base.schedule;

import com.study.base.schedule.task.MyJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.calendar.AnnualCalendar;
import java.util.GregorianCalendar;
import java.util.Calendar;

/**
 * @author xiaoqian
 *         Created by xq on 2017/10/19.
 */
public class QuarzSchedulerTest {
    public static void test() {
        try {
            //创建jobDetail实例，绑定Job实现类
            //指明job的名称，所在组的名称，以及绑定job类
            JobDetail jobDetail = JobBuilder.newJob(MyJob.class)
                    .withIdentity("job2", "jgroup1")
                    .usingJobData("name", "孙悟空")
                    .build();
            //第一个参数是任务名，用于引用该任务
            // 第二个参数是任务组名，这里使用默认名，任务组名用于引用集合起来的一组任务，如可以使用Scheduler.pauseJobGroup()来暂停一组任务，每个组中的任务名是唯一的
            // 第三个参数是实现特定任务的类。
//            JobDetail jobDetail = new JobDetail("job2", Scheduler.DEFAULT_GROUP, MyJob.class);

            Trigger trigger = null;
            int i = 1;
            if (1 == i) {       //使用simpleTrigger规则
                trigger = TriggerBuilder.newTrigger()
                        .withIdentity("simpleTrigger", "triggerGroup")
                        .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(2).withRepeatCount(36000))
                        .startNow()     //.startAt() 设定开始时间
                        .build();
            } else if (2 == i) {        //使用cornTrigger规则
                trigger = TriggerBuilder.newTrigger()
                        .withIdentity("cornTrigger", "cornGroup")
                        .withSchedule(CronScheduleBuilder.cronSchedule("3/1 * * * * ? *"))
                        .startNow()
                        .build();
            }

            //通过schedulerFactory获取一个调度器
            SchedulerFactory schedulerfactory = new StdSchedulerFactory();
            Scheduler scheduler = schedulerfactory.getScheduler();
            scheduler.scheduleJob(jobDetail, trigger);
            {
                // 创建假期日期
                AnnualCalendar holidays = new AnnualCalendar();

                Calendar halloween = new GregorianCalendar(2005, 9, 31);
                holidays.setDayExcluded(halloween, true);

                // 将此 holidays 告知调度者
                scheduler.addCalendar("holidays", holidays, false, false);
            }

            scheduler.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}