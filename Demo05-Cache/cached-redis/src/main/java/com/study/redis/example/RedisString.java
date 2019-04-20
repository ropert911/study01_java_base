package com.study.redis.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by xq on 2017/11/1.
 */
@Component
public class RedisString {
    Logger LOGGER = LoggerFactory.getLogger(RedisConn.class);

    @Autowired
    @Qualifier("xqredis")
    RedisTemplate<String, String> redisTemplate;

    //key value操作
    public void test() {
        LOGGER.error("RedisString====================Begin");
        {
            redisTemplate.opsForValue().set("StringTest:StringKey", "StringValue");
            String value1 = redisTemplate.opsForValue().get("StringTest:StringKey");
            LOGGER.error("获取值=>{}", value1);
            redisTemplate.delete("StringTest:StringKey");
        }

        //超时测试
        try {
            redisTemplate.opsForValue().set("StringTest:StringKey1", "StringValue1", 2, TimeUnit.SECONDS); //ttl 2s超时
            String value1 = redisTemplate.opsForValue().get("StringTest:StringKey1");
            LOGGER.error("添加 StringTest:StringKey1=>{}", value1);

            Thread.sleep(3000);
            value1 = redisTemplate.opsForValue().get("StringTest:StringKey1");
            LOGGER.error("3秒后 StringTest:StringKey1=>{}", value1);

            redisTemplate.delete("StringTest:StringKey1");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        //多键设置
        {
            Map<String, String> maps = new HashMap<String, String>();
            maps.put("StringTest:multi1", "multi1");
            maps.put("StringTest:multi2", "multi2");
            maps.put("StringTest:multi3", "multi3");
            redisTemplate.opsForValue().multiSet(maps);

            List<String> keys = new ArrayList<String>();
            keys.add("StringTest:multi1");
            keys.add("StringTest:multi2");
            keys.add("StringTest:multi3");
            LOGGER.error("同时获取多个值==>{}", redisTemplate.opsForValue().multiGet(keys));

            LOGGER.error("" + maps + "是否absent == {}", redisTemplate.opsForValue().multiSetIfAbsent(maps));
            Map<String, String> maps1 = new HashMap<>();
            maps1.put("StringTest:multi11", "multi11");
            maps1.put("StringTest:multi22", "multi22");
            maps1.put("StringTest:multi33", "multi33");
            LOGGER.error("maps1:{} 是否absent == {}", maps1, redisTemplate.opsForValue().multiSetIfAbsent(maps1));

            List<String> keys1 = new ArrayList<String>();
            keys1.add("StringTest:multi11");
            keys1.add("StringTest:multi22");
            keys1.add("StringTest:multi33");

            redisTemplate.delete(keys);
            redisTemplate.delete(keys1);
        }

        LOGGER.error("RedisString==============================End");
    }
}
