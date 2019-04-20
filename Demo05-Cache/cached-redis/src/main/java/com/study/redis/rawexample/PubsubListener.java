package com.study.redis.rawexample;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class PubsubListener extends JedisPubSub {
    @Override
    public void unsubscribe() {
        super.unsubscribe();
    }

    @Override
    public void unsubscribe(String... channels) {
        super.unsubscribe(channels);
    }

    @Override
    public void subscribe(String... channels) {
        super.subscribe(channels);
    }

    @Override
    public void psubscribe(String... patterns) {
        super.psubscribe(patterns);
    }

    @Override
    public void punsubscribe() {
        super.punsubscribe();
    }

    @Override
    public void punsubscribe(String... patterns) {
        super.punsubscribe(patterns);
    }

    @Override
    public void onMessage(String channel, String message) {
        System.out.println("channel:" + channel + "    receives message :" + message);
//        this.unsubscribe();
    }

    @Override
    public void onPMessage(String pattern, String channel, String message) {
        System.out.println("onPMessage==="+"channel:" + channel + "   pub message :" + message+"  pattern:"+pattern);
    }

    @Override
    public void onSubscribe(String channel, int subscribedChannels) {
        System.out.println("channel:" + channel + "is been subscribed:" + subscribedChannels);
    }

    @Override
    public void onPUnsubscribe(String pattern, int subscribedChannels) {
        System.out.println("pattern:" + pattern + "is been unPSubscribeed:" + subscribedChannels);
    }

    @Override
    public void onPSubscribe(String pattern, int subscribedChannels) {
        System.out.println("pattern:" + pattern + "is been PSubscribeed:" + subscribedChannels);
    }

    @Override
    public void onUnsubscribe(String channel, int subscribedChannels) {
        System.out.println("channel:" + channel + "is been unsubscribed:" + subscribedChannels);
    }

    public static void main(String[] args) throws Exception
    {

        Jedis jedis = new Jedis("192.168.17.128",6379);
//        jedis.auth("lexue@nosql");
        PubsubListener listener = new PubsubListener();
        jedis.subscribe(listener, "redisChatTest");
        System.out.println("test sub");
        //other code



    }
}
