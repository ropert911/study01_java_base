package com.study.cache.redis.example2;

import com.study.cache.redis.example.RedisConn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

/**
 * Created by xq on 2017/11/1.
 */
@Component
public class Reids1Transaction {
    Logger LOGGER = LoggerFactory.getLogger(RedisConn.class);

    @Autowired
    @Qualifier("xqredis")
    RedisTemplate<String, String> redisTemplate;

    /*
     * By default each template method call creates a new connection - so
     * WATCH, MUTLI, EXEC, UNWATCH won't work because of the missing
     * context. To make use of transaction support use SessionCallback which
     * reuses the underlying connection.
     */
    //watch, util,exec,unwatch要在同一个链接上才生效
    public void test() {
        //事件要明确声明：在连接池情况下，要通过这种方式老能绑定到一个连接上。（非连接池不涉及）
        redisTemplate.setEnableTransactionSupport(true);    //支持事件，这样就会绑定在同一个连接上
        {
            //监视一个或者多个key。如果在执行事务前，这些key被打断，那么事务执行失败
            redisTemplate.watch("user1");  //要在setEnableTransactionSupport后
//            redisTemplate.opsForValue().set("user1", "user5");   //这会引起事务的失败
            redisTemplate.multi();
            ValueOperations<String, String> tvo = redisTemplate.opsForValue();
            tvo.set("user1", "test");
            tvo.set("user2", "test");
            List<Object> objectList = redisTemplate.exec();
            LOGGER.error("test1-1 size=>{}", objectList.size());
        }

        {
            redisTemplate.watch("user1");
            redisTemplate.opsForValue().set("user1", "user5");
            redisTemplate.multi();
            ValueOperations<String, String> tvo = redisTemplate.opsForValue();
            tvo.set("user1", "user1");
            tvo.set("user2", "user2");
            List<Object> objectList = redisTemplate.exec();
            LOGGER.error("test1-2 size=>{}", objectList.size());
        }

        LOGGER.error("test1-1 === {}", redisTemplate.opsForValue().get("user1"));
        LOGGER.error("test1-2 === {}", redisTemplate.opsForValue().get("user2"));
        redisTemplate.delete("user1");
        redisTemplate.delete("user2");
        redisTemplate.setEnableTransactionSupport(false);
    }

    /**
     * 在连接池环境中，需要借助sessionCallback来绑定connection
     */
    public void test2() {
        LOGGER.error("");
        SessionCallback<List<Object>> sessionCallback = new SessionCallback<List<Object>>() {
            @Override
            public List<Object> execute(RedisOperations operations) throws DataAccessException {
                operations.watch("test2");
//                operations.opsForValue().set("test2", "test3");     //影响事务

                operations.multi();
                String user = "test2";
                String key = "test2";
                BoundValueOperations<String, String> oper = operations.boundValueOps(key);
                oper.set(user);
                oper.expire(60, TimeUnit.MINUTES);
                List<Object> list = operations.exec();
                LOGGER.error("test2-1 === {} {}", list.size(), list);
                return list;
            }
        };

        //可通过判断是否为空来判断事务是否成功，进一步确认是否重试
        if (0 == redisTemplate.execute(sessionCallback).size()) {
            LOGGER.error("test2-2 transaction failed");
        } else {
            LOGGER.error("test2-2 transaction success");
        }

        LOGGER.error("test2-3 === {}", redisTemplate.opsForValue().get("test2"));
        redisTemplate.delete("test2");
    }
}
