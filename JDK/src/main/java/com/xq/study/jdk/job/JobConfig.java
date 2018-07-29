package com.xq.study.jdk.job;

import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by sk-qianxiao on 2017/10/19.
 */
@Configuration
public class JobConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(JobConfig.class);

    @Autowired
    private ZookeeperRegistryCenter regCenter;

    @Bean
    public IndexJob createEJob1() {
        return new IndexJob();
    }


    @Bean(initMethod = "init")
    public JobScheduler createjob1Schedular(final IndexJob indexJob) {
        String cron = TriggerCron.getCron();
        int shareTotalCount = 1;

        //job配置
        LiteJobConfiguration liteJobConfiguration = LiteJobConfiguration.newBuilder(
                new SimpleJobConfiguration(
                        JobCoreConfiguration.newBuilder(indexJob.getClass().getName(), cron, shareTotalCount).build(),
                        indexJob.getClass().getCanonicalName()
                )
        ).overwrite(true).build();

        return new SpringJobScheduler(indexJob, regCenter, liteJobConfiguration);
    }
}
