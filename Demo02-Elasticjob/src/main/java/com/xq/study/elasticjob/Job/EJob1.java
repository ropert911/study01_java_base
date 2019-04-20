package com.xq.study.elasticjob.Job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by sk-qianxiao on 2017/10/19.
 */
public class EJob1 implements SimpleJob {

    private static final Logger LOGGER = LoggerFactory.getLogger(EJob1.class);


    @Override
    public void execute(ShardingContext shardingContext) {

        Integer shardindTotalCount = shardingContext.getShardingTotalCount();
        Integer shardingItem = shardingContext.getShardingItem();

        LOGGER.info("作业已创建----EJob1: shardindTotalCount:{},shardingItem: {}", shardindTotalCount, shardingItem);
    }
}