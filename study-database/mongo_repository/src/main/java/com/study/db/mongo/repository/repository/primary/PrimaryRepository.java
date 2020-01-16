package com.study.db.mongo.repository.repository.primary;

import com.study.db.mongo.repository.po.PrimaryMongoObject;
import org.springframework.data.mongodb.repository.MongoRepository;

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
