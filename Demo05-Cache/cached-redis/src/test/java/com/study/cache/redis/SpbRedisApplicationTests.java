package com.study.cache.redis;

import com.study.cache.redis.example.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpbRedisApplicationTests {

    @Autowired
    RedisConn redisConn;
    @Autowired
    RedisString redisString;
    @Autowired
    RedisHash redisHash;
    @Autowired
    RedisList redisList;
    @Autowired
    RedisSet redisSet;
    @Autowired
    RedisZSet redisZSet;

    @Test
    public void contextLoads() {
        redisConn.test();
        redisHash.test();
        redisList.test();
        redisSet.test();
        redisString.test();
        redisZSet.test();
    }
}
