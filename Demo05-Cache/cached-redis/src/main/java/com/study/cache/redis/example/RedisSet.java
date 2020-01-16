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
import java.util.List;
import java.util.Set;

/**
 * Created by xq on 2017/11/1.
 */
@Component
public class RedisSet {
    Logger LOGGER = LoggerFactory.getLogger(RedisConn.class);

    @Autowired
    @Qualifier("xqredis")
    RedisTemplate<String, String> redisTemplate;

    public void test() {
        //单个删除
        LOGGER.error("RedisSet=========Begin");
        for (String value : redisTemplate.opsForSet().members("SetTest:xqset1")) {
            redisTemplate.opsForSet().remove("SetTest:xqset1", value);
        }
        for (String value : redisTemplate.opsForSet().members("SetTest:xqset2")) {
            redisTemplate.opsForSet().remove("SetTest:xqset2", value);
        }

        redisTemplate.opsForSet().add("SetTest:xqset1", "abc1");
        redisTemplate.opsForSet().add("SetTest:xqset1", "abc2");
        redisTemplate.opsForSet().add("SetTest:xqset1", "abc3");
        LOGGER.error("xqset1的内容==>" + redisTemplate.opsForSet().members("SetTest:xqset1"));

        redisTemplate.opsForSet().add("SetTest:xqset2", "abc1");
        redisTemplate.opsForSet().add("SetTest:xqset2", "abc2");
        redisTemplate.opsForSet().add("SetTest:xqset2", "abc4");
        LOGGER.error("xqset2的内容==>" + redisTemplate.opsForSet().members("SetTest:xqset2"));

        Set<String> diff = redisTemplate.opsForSet().difference("SetTest:xqset1", "SetTest:xqset2");        //差集
        LOGGER.error("差集==>" + diff);
        //difference(K key, Collection<K> otherKeys);       //key无序集合与多个otherKey无序集合的差集
        //Long differenceAndStore(K key, K otherKey, K destKey);        //key无序集合与otherkey无序集合的差集存储到destKey无序集合中
        // differenceAndStore(K key, Collection<K> otherKeys, K destKey);   //key无序集合与多个otherkey无序集合的差集存储到destKey无序集合中

        Set<String> uni = redisTemplate.opsForSet().union("SetTest:xqset1", "SetTest:xqset2");              //并集
        LOGGER.error("并集==>" + uni);
        //unionAndStore(K key, K otherKey, K destKey);
        //unionAndStore(K key, Collection<K> otherKeys, K destKey);
        List<String> strlist = new ArrayList<String>();
        strlist.add("SetTest:xqset2");
        strlist.add("SetTest:xqset4");
        LOGGER.error("多个的并集=>" + redisTemplate.opsForSet().union("SetTest:xqset1", strlist));

        Set<String> intersect = redisTemplate.opsForSet().intersect("SetTest:xqset1", "SetTest:xqset2");        //交
        LOGGER.error("交集==>" + intersect);
        redisTemplate.opsForSet().intersectAndStore("SetTest:xqset1", "SetTest:xqset2", "SetTest:xqset4");   //key无序集合与otherkey无序集合的交集存储到destKey无序集合中
        LOGGER.error("交集==>" + redisTemplate.opsForSet().members("SetTest:xqset4"));

        LOGGER.error("随机获取=>" + redisTemplate.opsForSet().pop("SetTest:xqset1"));
        //randomMember(K key);      //随机获取key无序集合中的一个元素
        redisTemplate.opsForSet().move("SetTest:xqset1", "abc1", "SetTest:xqset3");
        LOGGER.error("原值=>" + redisTemplate.opsForSet().members("SetTest:xqset1") +
                "size:" + redisTemplate.opsForSet().size("SetTest:xqset1"));
        LOGGER.error("新的=>" + redisTemplate.opsForSet().members("SetTest:xqset3"));
        LOGGER.error("xqset1是不是有 abc3 => " + redisTemplate.opsForSet().isMember("SetTest:xqset1", "abc3"));
        //Set<V> distinctRandomMembers(K key, long count);          //获取多个key无序集合中的元素（去重），count表示个数
        //List<V> randomMembers(K key, long count);                 //获取多个key无序集合中的元素，count表示个数

        Cursor<String> curosr = redisTemplate.opsForSet().scan("SetTest:xqset1", ScanOptions.scanOptions().build());
        while (curosr.hasNext()) {
            LOGGER.error("遍历结果=>" + curosr.next());
        }

        redisTemplate.delete("SetTest:xqset1");
        redisTemplate.delete("SetTest:xqset2");
        redisTemplate.delete("SetTest:xqset3");
        redisTemplate.delete("SetTest:xqset4");
        LOGGER.error("RedisSet=========End");
    }

}
