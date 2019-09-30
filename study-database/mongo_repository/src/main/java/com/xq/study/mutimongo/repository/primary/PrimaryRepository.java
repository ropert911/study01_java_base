package com.xq.study.mutimongo.repository.primary;

import com.xq.study.mutimongo.po.PrimaryMongoObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author neo
 */
public interface PrimaryRepository extends MongoRepository<PrimaryMongoObject, String> {
    //支持自动生成 及 指定生成
    //指定生成--根据名字来
//    Person findByName(String name);
//
//      //指定生成，指定部分语句
//    @Query("{'age': ?0}")
//    List<Person> withQueryFindByAge(Integer age);

//    @Resource(name="stringRedisTemplate")		//注入基于字符串的简单属性操作方法
//    ValueOperations<String,String> valOpsStr; //3
//
//    @Resource(name="redisTemplate")			//基于对象的简单属性操作方法
//    ValueOperations<Object, Object> valOps; //4
}
