package com.study.redis.example2;

import com.study.redis.example.RedisConn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by xq on 2017/11/1.
 */
@Component
public class Reids3AutoComp {
    //自动补全功能
    Logger LOGGER = LoggerFactory.getLogger(RedisConn.class);

    @Autowired
    @Qualifier("xqredis")
    RedisTemplate<String, String> redisTemplate;

    //pipeline:备用方式
    public void add_user(String name) {
        SessionCallback<List<Object>> sessionCallback = new SessionCallback<List<Object>>() {
            @Override
            public List<Object> execute(RedisOperations operations) throws DataAccessException {
                operations.watch(name);

                operations.multi();
                redisTemplate.opsForList().remove("users", 1, name);
                redisTemplate.opsForList().leftPush("users", name);
                redisTemplate.opsForList().trim("users", 0, 10);
                List<Object> list = operations.exec();
                return list;
            }
        };

        redisTemplate.execute(sessionCallback);
    }

    List<String> list_user(String prefix) {
        List<String> all = new ArrayList<>();

        String pre = prefix.toLowerCase();
        List<String> object = redisTemplate.opsForList().range("users", 0, -1);
        for (String item : object) {
            if (item.toLowerCase().startsWith(pre)) {
                all.add(item);
            }
        }

        return all;
    }

    public void test() {
        add_user("a1");
        add_user("a2");
        add_user("a3");
        add_user("a2");
        add_user("b2");

        ;
        LOGGER.error("test2-2 transaction success=={}", list_user("a"));
    }
}
