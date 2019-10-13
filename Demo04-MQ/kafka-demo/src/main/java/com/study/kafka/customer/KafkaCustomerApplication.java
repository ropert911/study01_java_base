package com.study.kafka.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author xq
 */
@SpringBootApplication
@EnableScheduling
public class KafkaCustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaCustomerApplication.class, args);
	}
}
