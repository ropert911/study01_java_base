package com.study.kafkaplain.producer;

import com.study.kafkaplain.producer.T2Spring.T2Spring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
//public class KafkaOfficApplication {
//    public static void main(String[] args) {
//        T1PlainJava.test();
//    }
//}

@SpringBootApplication
public class KafkaOfficApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(KafkaOfficApplication.class, args).close();
    }

    @Autowired
    T2Spring t2Spring;

    @Override
    public void run(String... args) throws Exception {
        t2Spring.test();
    }
}
