package com.study.cache.redis.example2;

import com.study.cache.redis.example.RedisConn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisConnectionUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by xq on 2017/11/1.
 */
@Component
public class Reids2PipeLine {
    Logger LOGGER = LoggerFactory.getLogger(RedisConn.class);

    @Autowired
    @Qualifier("xqredis")
    RedisTemplate<String, String> redisTemplate;

    /**
     * pipeline : 1，正确使用方式
     */
    public void test1() {
        byte[] rawKey = new String("total").getBytes();
        //pipeline
        RedisCallback<List<Object>> pipelineCallback = new RedisCallback<List<Object>>() {
            @Override
            public List<Object> doInRedis(RedisConnection connection) throws DataAccessException {
                connection.openPipeline();
                connection.incr(rawKey);
                connection.incr(rawKey);
                return connection.closePipeline();
            }
        };

        List<Object> results = (List<Object>) redisTemplate.execute(pipelineCallback);
        for (Object item : results) {
            System.out.println("111=>" + item.toString());
        }
    }

    //pipeline:备用方式
    public void test2() {
        byte[] rawKey = new String("total").getBytes();
        RedisConnectionFactory factory = redisTemplate.getConnectionFactory();
        RedisConnection redisConnection = factory.getConnection();
        List<Object> results;
        try {
            redisConnection.openPipeline();
            redisConnection.incr(rawKey);
            results = redisConnection.closePipeline();
            redisConnection.del(rawKey);
        } finally {
            RedisConnectionUtils.releaseConnection(redisConnection, factory);
        }
        if (results == null) {
            return;
        }
        for (Object item : results) {
            System.out.println("222==>" + item.toString());
        }
    }
}
