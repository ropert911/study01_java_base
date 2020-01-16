package com.study.base.schedule.elasticjob.Job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.ddframe.job.lite.lifecycle.api.JobSettingsAPI;
import com.dangdang.ddframe.job.lite.lifecycle.domain.JobSettings;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by sk-qianxiao on 2017/10/19.
 */
public class EJob2 implements SimpleJob {

    private static final Logger LOGGER = LoggerFactory.getLogger(EJob2.class);

    @Autowired
    private ZookeeperRegistryCenter regCenter;
    @Autowired
    private JobSettingsAPI jobSettingsAPI;

    @Override
    public void execute(ShardingContext shardingContext) {
        List<String> jobNames = regCenter.getChildrenKeys("/");
        for (int i = 0; i < jobNames.size(); i++) {
            String jobName = jobNames.get(i);
            JobSettings jobSettings = jobSettingsAPI.getJobSettings(jobName);
            jobSettings.setCron("10/10 * * * * ?");
            LOGGER.info("作业 {} 配置变更", jobName);

            jobSettingsAPI.updateJobSettings(jobSettings);
        }
    }
}