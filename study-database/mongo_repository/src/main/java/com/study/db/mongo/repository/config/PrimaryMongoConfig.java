package com.study.db.mongo.repository.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author neo
 */
@Configuration
@EnableMongoRepositories(basePackages = "com.xq.study.mutimongo", //指明应用到哪个目录
		mongoTemplateRef = PrimaryMongoConfig.MONGO_TEMPLATE)
public class PrimaryMongoConfig {

	protected static final String MONGO_TEMPLATE = "primaryMongoTemplate";
}
