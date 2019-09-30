package com.study.scheduled.sprint_scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author sk-qianxiao
 */

@EnableScheduling
@Component
public class SchedulerTask {
    private static Logger LOGGER = LoggerFactory.getLogger(SchedulerTask.class);

    public final static long ONE_Minute = 10 * 1000;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

     @Scheduled(fixedDelay = ONE_Minute)
    public void fixedDelayJob() {
        LOGGER.error("sprint_scheduler 固定延迟 fixedDelay {}", dateFormat.format(new Date()));
         try {
             Thread.sleep(5000);
         } catch (InterruptedException e) {
             e.printStackTrace();
         }
     }

    @Scheduled(fixedRate = ONE_Minute)
    public void fixedRateJob() {
        LOGGER.error("sprint_scheduler 固定周期 fixedRateJob {}", dateFormat.format(new Date()));
    }

    private int count = 0;
    @Scheduled(cron = "*/10 * * * * ?")
    private void process() {
        LOGGER.error("sprint_scheduler cron方式运行 {} ", (count++));
    }


}
