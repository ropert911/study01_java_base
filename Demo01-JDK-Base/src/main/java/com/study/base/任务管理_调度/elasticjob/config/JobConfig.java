package com.study.base.任务管理_调度.elasticjob.config;

import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import com.study.base.任务管理_调度.elasticjob.Job.EJob1;
import com.study.base.任务管理_调度.elasticjob.Job.EJob2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by sk-qianxiao on 2017/10/19.
 */
@Configuration
public class JobConfig {
    @Autowired
    private ZookeeperRegistryCenter regCenter;

    @Bean
    public EJob1 createEJob1() {
        return new EJob1();
    }

    @Bean
    public EJob2 createEJob2() {
        return new EJob2();
    }

    @Bean(initMethod = "init")
    public JobScheduler createjob1Schedular(final EJob1 job1) {
        String cron = "5/5 * * * * ?";
        //任务分成2片来做
        int shareTotalCount = 2;

        //job配置
        LiteJobConfiguration liteJobConfiguration = LiteJobConfiguration.newBuilder(
                new SimpleJobConfiguration(
                        JobCoreConfiguration.newBuilder(job1.getClass().getName(), cron, shareTotalCount).build(),
                        job1.getClass().getCanonicalName()
                )
        ).overwrite(true).build();

        return new SpringJobScheduler(job1, regCenter, liteJobConfiguration);
    }

    @Bean(initMethod = "init")
    public JobScheduler createjob2Schedular(final EJob2 job2) {
        String cron = "10/10 * * * * ?";
        //任务分成2片来做
        int shareTotalCount = 2;

        //job配置
        LiteJobConfiguration liteJobConfiguration = LiteJobConfiguration.newBuilder(
                new SimpleJobConfiguration(
                        JobCoreConfiguration.newBuilder(job2.getClass().getName(), cron, shareTotalCount).build(),
                        job2.getClass().getCanonicalName()
                )
        ).overwrite(true).build();

        return new SpringJobScheduler(job2, regCenter, liteJobConfiguration);
    }
}
