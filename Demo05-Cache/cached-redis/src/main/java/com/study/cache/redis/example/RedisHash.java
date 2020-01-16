package com.study.cache.redis.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xq on 2017/11/1.
 */
@Component
public class RedisHash {
    Logger LOGGER = LoggerFactory.getLogger(RedisConn.class);

    @Autowired
    @Qualifier("xqredis")
    RedisTemplate<String, String> redisTemplate;

    public void test() {
        LOGGER.error("RedisHash=========Begin");
        redisTemplate.opsForHash().put("HashTest:redisHash", "name", "tom");
        redisTemplate.opsForHash().put("HashTest:redisHash", "age", 26);
        redisTemplate.opsForHash().put("HashTest:redisHash", "class", "6");
        LOGGER.error("redisHash 内容=>" + redisTemplate.opsForHash().entries("HashTest:redisHash"));

        Map<String, Object> testMap = new HashMap();
        testMap.put("name", "jack");
        testMap.put("age", 27);
        testMap.put("class", "1");
        redisTemplate.opsForHash().putAll("HashTest:redisHash1", testMap);


        LOGGER.error("redisHash1 删除name结果=>" + redisTemplate.opsForHash().delete("HashTest:redisHash1", "name"));       //删除给定的哈希hashKeys
        LOGGER.error("redisHash1 内容=>" + redisTemplate.opsForHash().entries("HashTest:redisHash1"));

        //确定哈希hashKey是否存在
        LOGGER.error("redisHash age是否存在=>" + redisTemplate.opsForHash().hasKey("HashTest:redisHash", "age") +
                " age值=>" + redisTemplate.opsForHash().get("HashTest:redisHash", "age"));
        LOGGER.error("redisHash ttt是否在在=>" + redisTemplate.opsForHash().hasKey("HashTest:redisHash", "ttt"));

        //从哈希中获取给定hashKey的值
        List<Object> kes = new ArrayList<Object>();
        kes.add("name");
        kes.add("age");
        kes.add("class");
        LOGGER.error("redisHash multiGet结果 => " + redisTemplate.opsForHash().multiGet("HashTest:redisHash", kes));

        //通过给定的delta增加散列hashKey的值（整型）
        System.out.println(redisTemplate.opsForHash().increment("HashTest:redisHash", "age", 1));
        LOGGER.error("redisHash 年龄加1后内容=>" + redisTemplate.opsForHash().entries("HashTest:redisHash"));

        //通过给定的delta增加散列hashKey的值（浮点数）
        System.out.println(redisTemplate.opsForHash().increment("HashTest:redisHash", "age", 1.1));
        LOGGER.error("redisHash 年龄加1.1后内容=>" + redisTemplate.opsForHash().entries("HashTest:redisHash"));

        //仅当hashKey不存在时才设置散列hashKey的值。
        redisTemplate.opsForHash().putIfAbsent("HashTest:redisHash", "age", 30);
        redisTemplate.opsForHash().putIfAbsent("HashTest:redisHash", "kkk", "kkk");
        LOGGER.error("redisHash putIfAbsent后 内容=>" + redisTemplate.opsForHash().entries("HashTest:redisHash"));

        //获取key所对应的散列表的key和大小
        LOGGER.error("redisHash所有key=>" + redisTemplate.opsForHash().keys("HashTest:redisHash"));
        LOGGER.error("redisHash所有key size=>" + redisTemplate.opsForHash().size("HashTest:redisHash"));

        //获取整个哈希存储的值根据keys
        LOGGER.error("redisHash所有value=>" + redisTemplate.opsForHash().values("HashTest:redisHash"));
        LOGGER.error("redisHash 内容=>" + redisTemplate.opsForHash().entries("HashTest:redisHash"));

        //使用Cursor在key的hash中迭代，相当于迭代器
        Cursor<Map.Entry<Object, Object>> curosr = redisTemplate.opsForHash().scan("HashTest:redisHash", ScanOptions.scanOptions().build());
        while (curosr.hasNext()) {
            Map.Entry<Object, Object> entry = curosr.next();
            LOGGER.error("迭代方式获取key:值=>" + entry.getKey() + ":" + entry.getValue());
        }

        redisTemplate.delete("HashTest:redisHash");
        redisTemplate.delete("HashTest:redisHash1");
        LOGGER.error("RedisHash=========End");
    }
}
