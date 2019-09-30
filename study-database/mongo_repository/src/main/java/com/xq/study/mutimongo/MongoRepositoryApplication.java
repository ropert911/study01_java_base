package com.xq.study.mutimongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@SpringBootApplication(exclude = MongoAutoConfiguration.class)
public class MongoRepositoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongoRepositoryApplication.class, args);
	}
}