package com.study.redis.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by xq on 2017/11/1.
 */
@Component
public class RedisZSet {
    Logger LOGGER = LoggerFactory.getLogger(RedisConn.class);

    @Autowired
    @Qualifier("xqredis")
    RedisTemplate<String, String> redisTemplate;

    public void test() {
        LOGGER.error("RedisZSet=========Begin");
        {
            //新增一个有序集合，存在的话为false，不存在的话为true
            redisTemplate.opsForZSet().add("zset1", "zset-1", 1.0);
            LOGGER.error("新增一个有序集合=>" + redisTemplate.opsForZSet().range("zset1", 0, -1));
            redisTemplate.delete("zset1");
        }

        {
            ZSetOperations.TypedTuple<String> objectTypedTuple1 = new DefaultTypedTuple<>("zset-5", 9.6);
            ZSetOperations.TypedTuple<String> objectTypedTuple2 = new DefaultTypedTuple<>("zset-6", 9.9);
            Set<ZSetOperations.TypedTuple<String>> tuples = new HashSet<>();
            tuples.add(objectTypedTuple1);
            tuples.add(objectTypedTuple2);
            redisTemplate.opsForZSet().add("zset1", tuples);        //新增一个有序集合
            LOGGER.error("新增一个有序集合=>" + redisTemplate.opsForZSet().range("zset1", 0, -1));
        }

        LOGGER.error("删除一个=>{}", redisTemplate.opsForZSet().remove("zset1", "zset-6"));        //从有序集合中移除一个或者多个元素
        LOGGER.error("删除 zset-6 后 => " + redisTemplate.opsForZSet().range("zset1", 0, -1));

        redisTemplate.opsForZSet().incrementScore("zset1", "zset-1", 1.1);  //原为1.1           //增加元素的score值，并返回增加后的值
        LOGGER.error("添加zset-1后 => " + redisTemplate.opsForZSet().range("zset1", 0, -1));

        LOGGER.error("成员=>{}", redisTemplate.opsForZSet().range("zset1", 0, -1));     //返回有序集中指定成员的排名，其中有序集成员按分数值递增(从小到大) 顺序排列
        LOGGER.error("zset-1的排名=>" + redisTemplate.opsForZSet().rank("zset1", "zset-1"));
        LOGGER.error("zset-1的反向排名=>" + redisTemplate.opsForZSet().reverseRank("zset1", "zset-1"));

        //通过分数返回有序集合指定区间内的成员，其中有序集成员按分数值递增(从小到大) 顺序排列
        LOGGER.error("成绩0-5的有=>" + redisTemplate.opsForZSet().rangeByScore("zset1", 0, 5));
        LOGGER.error("成绩0-5的有=>" + redisTemplate.opsForZSet().rangeByScore("zset1", 0, 5, 1, 2));
        //通过索引区间返回有序集合成指定区间内的成员，其中有序集成员按分数值递减(从大到小) 顺序排列
        LOGGER.error("reverseRange=>" + redisTemplate.opsForZSet().reverseRange("zset1", 0, -1));

        //通过索引区间返回有序集合成指定区间内的成员对象，其中有序集成员按分数值递增(从小到大) 顺序排列
        Set<ZSetOperations.TypedTuple<String>> tuples = redisTemplate.opsForZSet().rangeWithScores("zset1", 0, -1);
        Iterator<ZSetOperations.TypedTuple<String>> iterator1 = tuples.iterator();
        while (iterator1.hasNext()) {
            ZSetOperations.TypedTuple<String> typedTuple = iterator1.next();
            LOGGER.error("遍历1-->value:" + typedTuple.getValue() + "  score:" + typedTuple.getScore());
        }

        //通过分数返回有序集合指定区间内的成员对象，其中有序集成员按分数值递增(从小到大) 顺序排列
        {
            Set<ZSetOperations.TypedTuple<String>> tuples2 = redisTemplate.opsForZSet().rangeByScoreWithScores("zset1", 0, 5);
            Iterator<ZSetOperations.TypedTuple<String>> iterator2 = tuples2.iterator();
            while (iterator2.hasNext()) {
                ZSetOperations.TypedTuple<String> typedTuple = iterator2.next();
                LOGGER.error("遍历2-->value:" + typedTuple.getValue() + "  score:" + typedTuple.getScore());
            }
        }

        //通过分数返回有序集合指定区间内的成员对象，并在索引范围内，其中有序集成员按分数值递增(从小到大) 顺序排列
        {
            Set<ZSetOperations.TypedTuple<String>> tuples3 = redisTemplate.opsForZSet().rangeByScoreWithScores("zset1", 0, 5, 1, 2);
            Iterator<ZSetOperations.TypedTuple<String>> iterator3 = tuples3.iterator();
            while (iterator3.hasNext()) {
                ZSetOperations.TypedTuple<String> typedTuple = iterator3.next();
                LOGGER.error("遍历3-->value:" + typedTuple.getValue() + "score:" + typedTuple.getScore());
            }
        }


        //通过索引区间返回有序集合成指定区间内的成员对象，其中有序集成员按分数值递减(从大到小) 顺序排列
        {
            Set<ZSetOperations.TypedTuple<String>> tuples4 = redisTemplate.opsForZSet().reverseRangeWithScores("zset1", 0, -1);
            Iterator<ZSetOperations.TypedTuple<String>> iterator4 = tuples4.iterator();
            while (iterator4.hasNext()) {
                ZSetOperations.TypedTuple<String> typedTuple = iterator4.next();
                LOGGER.error("遍历5 => value:" + typedTuple.getValue() + " score:" + typedTuple.getScore());
            }
        }

        //与rangeByScore调用方法一样，其中有序集成员按分数值递减(从大到小) 顺序排列
        //Set<TypedTuple<V>> reverseRangeByScoreWithScores (K key,double min, double max);
        //与rangeByScoreWithScores调用方法一样，其中有序集成员按分数值递减(从大到小) 顺序排列
        // Set<V> reverseRangeByScore (K key,double min, double max, long offset, long count);
        //与rangeByScore调用方法一样，其中有序集成员按分数值递减(从大到小) 顺序排列
        //Set<TypedTuple<V>> reverseRangeByScoreWithScores (K key,double min, double max, long offset, long count);
        //与rangeByScoreWithScores调用方法一样，其中有序集成员按分数值递减(从大到小) 顺序排列

        //通过分数返回有序集合指定区间内的成员个数
        LOGGER.error("{}", redisTemplate.opsForZSet().rangeByScore("zset1", 0, 5));
        LOGGER.error("{}", redisTemplate.opsForZSet().count("zset1", 0, 5));

        //获取有序集合的成员数，内部调用的就是zCard方法
        LOGGER.error("{}", redisTemplate.opsForZSet().size("zset1"));

        //获取有序集合的成员数
        LOGGER.error("{}", redisTemplate.opsForZSet().zCard("zset1"));

        //获取指定成员的score值
        LOGGER.error("{}", redisTemplate.opsForZSet().score("zset1", "zset-1"));

        //移除指定索引位置的成员，其中有序集成员按分数值递增(从小到大) 顺序排列
        LOGGER.error("{}", redisTemplate.opsForZSet().range("zset2", 0, -1));
        LOGGER.error("{}", redisTemplate.opsForZSet().removeRange("zset2", 1, 2));
        LOGGER.error("{}", redisTemplate.opsForZSet().range("zset2", 0, -1));

        //根据指定的score值得范围来移除成员
//        //System.out.println(template.opsForZSet().add("zset2","zset-1",1.1));
//        //System.out.println(template.opsForZSet().add("zset2","zset-2",1.2));
//        //System.out.println(template.opsForZSet().add("zset2","zset-3",2.3));
//        //System.out.println(template.opsForZSet().add("zset2","zset-4",6.6));
//        System.out.println(redisTemplate.opsForZSet().range("zset2", 0, -1));
//        System.out.println(redisTemplate.opsForZSet().removeRangeByScore("zset2", 2, 3));
//        System.out.println(redisTemplate.opsForZSet().range("zset2", 0, -1));
//
//        //计算给定的一个有序集的并集，并存储在新的 destKey中，key相同的话会把score值相加
//        System.out.println(redisTemplate.opsForZSet().add("zzset1", "zset-1", 1.0));
//        System.out.println(redisTemplate.opsForZSet().add("zzset1", "zset-2", 2.0));
//        System.out.println(redisTemplate.opsForZSet().add("zzset1", "zset-3", 3.0));
//        System.out.println(redisTemplate.opsForZSet().add("zzset1", "zset-4", 6.0));
//
//        System.out.println(redisTemplate.opsForZSet().add("zzset2", "zset-1", 1.0));
//        System.out.println(redisTemplate.opsForZSet().add("zzset2", "zset-2", 2.0));
//        System.out.println(redisTemplate.opsForZSet().add("zzset2", "zset-3", 3.0));
//        System.out.println(redisTemplate.opsForZSet().add("zzset2", "zset-4", 6.0));
//        System.out.println(redisTemplate.opsForZSet().add("zzset2", "zset-5", 7.0));
//        System.out.println(redisTemplate.opsForZSet().unionAndStore("zzset1", "zzset2", "destZset11"));

        {
            Set<ZSetOperations.TypedTuple<String>> tuples2 = redisTemplate.opsForZSet().rangeWithScores("destZset11", 0, -1);
            Iterator<ZSetOperations.TypedTuple<String>> iterator2 = tuples2.iterator();
            while (iterator2.hasNext()) {
                ZSetOperations.TypedTuple<String> typedTuple = iterator2.next();
                LOGGER.error("遍历6 => value:" + typedTuple.getValue() + "score:" + typedTuple.getScore());
            }
        }

        //计算给定的多个有序集的并集，并存储在新的 destKey中
        //System.out.println(template.opsForZSet().add("zzset1","zset-1",1.0));
        //System.out.println(template.opsForZSet().add("zzset1","zset-2",2.0));
        //System.out.println(template.opsForZSet().add("zzset1","zset-3",3.0));
        //System.out.println(template.opsForZSet().add("zzset1","zset-4",6.0));
        //
        //System.out.println(template.opsForZSet().add("zzset2","zset-1",1.0));
        //System.out.println(template.opsForZSet().add("zzset2","zset-2",2.0));
        //System.out.println(template.opsForZSet().add("zzset2","zset-3",3.0));
        //System.out.println(template.opsForZSet().add("zzset2","zset-4",6.0));
        //System.out.println(template.opsForZSet().add("zzset2","zset-5",7.0));

        LOGGER.error("{}", redisTemplate.opsForZSet().add("zzset3", "zset-1", 1.0));
        LOGGER.error("{}", redisTemplate.opsForZSet().add("zzset3", "zset-2", 2.0));
        LOGGER.error("{}", redisTemplate.opsForZSet().add("zzset3", "zset-3", 3.0));
        LOGGER.error("{}", redisTemplate.opsForZSet().add("zzset3", "zset-4", 6.0));
        LOGGER.error("{}", redisTemplate.opsForZSet().add("zzset3", "zset-5", 7.0));

        List<String> stringList = new ArrayList<String>();
        stringList.add("zzset2");
        stringList.add("zzset3");
        LOGGER.error("{}", redisTemplate.opsForZSet().unionAndStore("zzset1", stringList, "destZset22"));

        {
            Set<ZSetOperations.TypedTuple<String>> tuples5 = redisTemplate.opsForZSet().rangeWithScores("destZset22", 0, -1);
            Iterator<ZSetOperations.TypedTuple<String>> iterator5 = tuples5.iterator();
            while (iterator5.hasNext()) {
                ZSetOperations.TypedTuple<String> typedTuple = iterator5.next();
                LOGGER.error("遍历7 => value:" + typedTuple.getValue() + "score:" + typedTuple.getScore());
            }
        }

        //计算给定的一个或多个有序集的交集并将结果集存储在新的有序集合 key 中
        LOGGER.error("intersectAndStore结果{}",redisTemplate.opsForZSet().intersectAndStore("zzset1", "zzset2", "destZset33"));

        {
            Set<ZSetOperations.TypedTuple<String>> tuples6 = redisTemplate.opsForZSet().rangeWithScores("destZset33", 0, -1);
            Iterator<ZSetOperations.TypedTuple<String>> iterator6 = tuples6.iterator();
            while (iterator6.hasNext()) {
                ZSetOperations.TypedTuple<String> typedTuple = iterator6.next();
                LOGGER.error("遍历8 => value:" + typedTuple.getValue() + "  score:" + typedTuple.getScore());
            }
        }

        //计算给定的一个或多个有序集的交集并将结果集存储在新的有序集合 key 中
        List<String> stringList2 = new ArrayList<String>();
        stringList2.add("zzset2");
        stringList2.add("zzset3");
        LOGGER.error("{}", redisTemplate.opsForZSet().intersectAndStore("zzset1", stringList2, "destZset44"));

        {
            Set<ZSetOperations.TypedTuple<String>> tuples7 = redisTemplate.opsForZSet().rangeWithScores("destZset44", 0, -1);
            Iterator<ZSetOperations.TypedTuple<String>> iterator7 = tuples7.iterator();
            while (iterator7.hasNext()) {
                ZSetOperations.TypedTuple<String> typedTuple = iterator7.next();
                LOGGER.error("遍历9 => value:" + typedTuple.getValue() + " score:" + typedTuple.getScore());
            }
        }

        {
            Cursor<ZSetOperations.TypedTuple<String>> cursor = redisTemplate.opsForZSet().scan("zzset1", ScanOptions.NONE);       //遍历zset
            while (cursor.hasNext()) {
                ZSetOperations.TypedTuple<String> item = cursor.next();
                LOGGER.error("遍历10 => " + item.getValue() + ":" + item.getScore());
            }
        }

        {
            redisTemplate.delete("zset1");
            redisTemplate.delete("zzset1");
            redisTemplate.delete("zzset3");
            redisTemplate.delete("destZset22");
        }
        LOGGER.error("RedisZSet=========End");
    }
}
