package com.study.redis.rawexample;

import redis.clients.jedis.Jedis;

public class TestSubscribe {

    public static void main(String args[]) throws Exception{
        Jedis jedis = new Jedis("192.168.17.128",6379);
//        jedis.auth("lexue@nosql");
        jedis.publish("redisChatTest", "我是天才");
        Thread.sleep(5000);
        jedis.publish("redisChatTest", "我牛逼");
        Thread.sleep(5000);
        jedis.publish("redisChatTest", "哈哈");
    }
}