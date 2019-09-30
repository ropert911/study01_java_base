package com.study.redis.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.*;

/**
 * @author sk-qianxiao
 */
@Configuration
public class RedisConfiguration {
    @Bean
    @Qualifier("xqredisFac")
    public RedisConnectionFactory createRedisCF2() {
        JedisConnectionFactory rCF = new JedisConnectionFactory();
        rCF.setHostName("192.168.40.80");
        rCF.setPort(6379);
        return rCF;
    }

    @Bean
    @Qualifier("xqredis")
    public RedisTemplate<String, String> createTemplate() {
        RedisConnectionFactory cf = createRedisCF2();
        RedisTemplate<String, String> redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(cf);     //设置连接属性

        //自定义的序列化
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);

        //默认使用  JdkSerializationRedisSerializer
        //还有下面的
//        GenericJackson2JsonRedisSerializer;
//        Jackson2JsonRedisSerializer;
//        OxmSerializer;
//        StringRedisSerializer;

        //自定义的序列化---StringRedisSerializer
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        redisTemplate.setValueSerializer(new StringRedisSerializer());
//        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
//        redisTemplate.setHashValueSerializer(new StringRedisSerializer());

        //设置自定义的序列化--Jackson2JsonRedisSerializer
        redisTemplate.setKeySerializer(jackson2JsonRedisSerializer);
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashKeySerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);



        redisTemplate.afterPropertiesSet();

        return redisTemplate;
    }

    @Bean
    public StringRedisTemplate createSt() {
        //建自定义的序列化
        RedisSerializer<String> stringSerializer = new StringRedisSerializer();

        RedisConnectionFactory cf = createRedisCF2();
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate(cf);
        //设置序列化
//        stringRedisTemplate.setKeySerializer(stringSerializer );
//        stringRedisTemplate.setValueSerializer(stringSerializer );
//        stringRedisTemplate.setHashKeySerializer(stringSerializer );
//        stringRedisTemplate.setHashValueSerializer(stringSerializer );

        return stringRedisTemplate;
    }
}
