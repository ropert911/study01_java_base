package com.xq.study.mutimongo.config;

import com.mongodb.MongoClient;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

/**
 * @author neo
 */
@Configuration
public class Config {

    @Primary
    @Bean(name = PrimaryMongoConfig.MONGO_TEMPLATE)
    public MongoTemplate primaryMongoTemplate() throws Exception {
        MongoProperties mongoProperties = new MongoProperties();
        mongoProperties.setHost("192.168.0.104");
        mongoProperties.setPort(27017);
        mongoProperties.setDatabase("test");

        MongoDbFactory mongoDbFactory = primaryFactory(mongoProperties);
        return new MongoTemplate(mongoDbFactory);
    }

    @Bean
    @Primary
    public MongoDbFactory primaryFactory(MongoProperties mongo) throws Exception {
        return new SimpleMongoDbFactory(new MongoClient(mongo.getHost(), mongo.getPort()),
                mongo.getDatabase());
    }


//    //MongoClient是Mongo的子类
//    @Bean
//    public MongoClient client() throws UnknownHostException {
//        MongoClient client = new MongoClient(new ServerAddress("127.0.0.1", 27017));
//        return client;
//    }
//
//    @Bean
//    public MongoDbFactory mongoDbFactory() throws Exception{
//        //获取数据库
//        String database = new MongoClientURI("mongodb://localhost/test").getDatabase();
//        return new SimpleMongoDbFactory(client(), database);
//    }
//
//    @Bean(name = "pm_common_mongotemplate")
//    public MongoTemplate mongoTemplate(Mongo mongo) throws Exception {
//        return new MongoTemplate(mongo, "dbname");
//    }
//
//    @Bean(name = "pm_common_mongotemplate2")
//    public MongoTemplate mongoTemplate2(Mongo mongo) throws Exception {
//        return new MongoTemplate(mongoDbFactory());
//    }

}
