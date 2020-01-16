package com.study.cache.redis_sentinelpool;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

@RunWith(SpringRunner.class)
@SpringBootTest
public class example2Tests {

    @Autowired
    private RedisPool redisPool;

    @Test
    public void contextLoads() {
        Jedis jedis1 = redisPool.getClient(4);
        jedis1.close();
        Jedis jedis2 = redisPool.getClient(4);
        Jedis jedis3 = redisPool.getClient(4);
        Jedis jedis4 = redisPool.getClient(4);
        Jedis jedis5 = redisPool.getClient(4);
        Jedis jedis6 = redisPool.getClient(4);
        Jedis jedis7 = redisPool.getClient(4);
        Jedis jedis8 = redisPool.getClient(4);
        Jedis jedis9 = redisPool.getClient(4);
        //另一种回收方式   jedisSentinelPool.returnBrokenResource(jedis);
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaa");
        String json = jedis9.get("aaa");
    }
}
