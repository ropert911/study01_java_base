package com.study.kafka.producer;

import com.study.kafka.producer.simple1.Producer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KafkaProducerApplicationTests {

    @Autowired
    Producer producer;

    @Test
    public void contextLoads() {
        for (int i = 0; i < 10; ++i) {
            String msg = "测试消息->" + i;
            producer.sendData(msg.getBytes());
        }
    }
}
