package com.study.kafkaplain.producer;

import com.study.kafkaplain.producer.T2Spring.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author xq
 */
@SpringBootApplication
@EnableScheduling
public class KafkaOfficApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(KafkaOfficApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        T1PlainJava.test();
    }
}
