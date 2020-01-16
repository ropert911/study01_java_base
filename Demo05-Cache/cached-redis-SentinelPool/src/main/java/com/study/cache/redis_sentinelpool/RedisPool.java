package com.study.cache.redis_sentinelpool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
public class RedisPool {

    private JedisSentinelPool jedisSentinelPool;

    /**
     * 初始化Sentinel连接
     */
    @PostConstruct
    protected void initPool() {
        // jedis 会把本地地址全部替换为本机IP
        String localhost = HostAndPort.LOCALHOST_STR;
        try {
            Field field = HostAndPort.class.getField("LOCALHOST_STR");
            field.setAccessible(true);
            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
            field.set(null, "127.0.0.1");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Set<String> set = new HashSet();
        set.add("192.168.20.91:26379");

        jedisSentinelPool = new JedisSentinelPool("mymaster", set);
    }

    /**
     * 得到Jedis连接客户端
     * @param db XXX
     * @return xxx
     */
    public Jedis getClient(int db) {
        Jedis jedis = jedisSentinelPool.getResource();
        jedis.select(db);
        return jedis;
    }
}