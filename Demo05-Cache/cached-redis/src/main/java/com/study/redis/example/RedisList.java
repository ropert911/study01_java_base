package com.study.redis.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by xq on 2017/11/1.
 */
@Component
public class RedisList {
    Logger LOGGER = LoggerFactory.getLogger(RedisConn.class);

    @Autowired
    @Qualifier("xqredis")
    RedisTemplate<String, String> redisTemplate;

    public void test() {
        LOGGER.error("RedisList======================Begin");
        {
            redisTemplate.opsForList().rightPush("ListTest:xqlist", "listvalue2");               //尾部添加
            redisTemplate.opsForList().rightPush("ListTest:xqlist", "listvalue3");               //尾部添加
            redisTemplate.opsForList().rightPush("ListTest:xqlist", "listvalue4");               //尾部添加
            redisTemplate.opsForList().leftPush("ListTest:xqlist", "listvalue1");                //头部添加
            String[] stringarrays = new String[]{"1", "2", "3"};
            redisTemplate.opsForList().leftPushAll("ListTest:xqlist", stringarrays);
            //只有存在key对应的列表才能将这个value值插入到key所对应的列表中
            redisTemplate.opsForList().leftPushIfPresent("ListTest:xqlist", "aaa");
            redisTemplate.opsForList().leftPushIfPresent("ListTest:xqlist2", "aaa");
            LOGGER.error("List大小=>{}", redisTemplate.opsForList().size("ListTest:xqlist"));
            LOGGER.error("List值=>{}", redisTemplate.opsForList().range("ListTest:xqlist", 0, -1));
        }
        {
            //保留[1,end)，即裁剪第一个元素
            redisTemplate.opsForList().trim("ListTest:xqlist", 1, -1);
            LOGGER.error("List裁剪后=>" + redisTemplate.opsForList().range("ListTest:xqlist", 0, -1));
        }
        {
            //尾部弹出
            String value2 = redisTemplate.opsForList().rightPop("ListTest:xqlist");
            LOGGER.error("尾部弹出的值 {}", value2);
            LOGGER.error("尾部弹出后=>" + redisTemplate.opsForList().range("ListTest:xqlist", 0, -1));
        }
        {
            //将删除列表中存储的列表中第一次次出现的“listvalue1”
            redisTemplate.opsForList().remove("ListTest:xqlist", 1, "listvalue1");
            LOGGER.error("删除listvalue1后=>{}", redisTemplate.opsForList().range("ListTest:xqlist", 0, -1));
            LOGGER.error("根据下标 2 获取值=>" + redisTemplate.opsForList().index("ListTest:xqlist", 2));
        }
        {
            redisTemplate.opsForList().rightPopAndLeftPush("ListTest:xqlist", "ListTest:xqlist2");        //用于移除列表的最后一个元素，并将该元素添加到另一个列表并返回
            LOGGER.error("rightPopAndLeftPush 后的 xqlist=>{}", redisTemplate.opsForList().range("ListTest:xqlist", 0, -1));
            LOGGER.error("rightPopAndLeftPush 后的 xqlist2=>{}", redisTemplate.opsForList().range("ListTest:xqlist2", 0, -1));
        }
        {
            redisTemplate.delete("ListTest:xqlist");
            redisTemplate.delete("ListTest:xqlist2");
        }
        LOGGER.error("RedisList==================End");
    }
}
