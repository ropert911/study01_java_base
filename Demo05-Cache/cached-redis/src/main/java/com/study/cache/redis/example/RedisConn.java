package com.study.cache.redis.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.stereotype.Component;

/**
 * Created by xq on 2017/11/1.
 */
@Component
public class RedisConn {
    Logger LOGGER = LoggerFactory.getLogger(RedisConn.class);

    @Autowired
    @Qualifier("xqredisFac")
    RedisConnectionFactory rCF;

    public void test() {
        LOGGER.error("RedisConn=======================Begin");
        //添加
        RedisConnection conn = rCF.getConnection();
        conn.set("greeting".getBytes(), "Hello world".getBytes());

        //查询
        byte[] gByts = conn.get("greeting".getBytes());
        String value = new String(gByts);
        LOGGER.error("greeting的值=={}", value);

        //删除
        conn.del("greeting".getBytes());
        LOGGER.error("RedisConn============================End");
    }
}
