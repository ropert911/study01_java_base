package com.xq.work.sendevent.demoworksendevent;

import com.alibaba.fastjson.JSON;
import com.xq.work.sendevent.demoworksendevent.dto.EventInfo;
import com.xq.work.sendevent.demoworksendevent.kafka.KafkaOperations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Calendar;

/**
 * @author sk-qianxiao
 * @date 2019/11/6
 */
@Component
public class InitProject implements ApplicationRunner {
    private static final Logger logger = LoggerFactory.getLogger(InitProject.class);

    @Value("${iot.kafka.producer.topic.origin.data}")
    private String originData;

    @Autowired
    private KafkaOperations kafkaOperations;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        LocalDateTime nowTime = LocalDateTime.now(ZoneId.of("UTC"));
        EventInfo eventInfo = EventInfo.builder()
//                .eventId(700001)        //温度
//                .eventId(700002)        //湿度
//                .eventId(700003)        //水位
                .eventId(700004)        //水压
                .deviceMac("12456789")
                .eventLastTime(nowTime.toEpochSecond(ZoneOffset.UTC))
                .eventFirstTime(nowTime.toEpochSecond(ZoneOffset.UTC))
                .eventFreq(333)
                .sensorType("sensorType_xq")    //设备类型
                .description("description_xq")  //事件描述
                .probReason("probReason_xq")    //可能原因
                .specReason("specReason_xq")   //确认原因
                .build();
        kafkaOperations.sendData(originData, (eventInfo.getDeviceMac() + "_" + eventInfo.getEventId()).getBytes(), JSON.toJSONString(eventInfo).getBytes());
    }
}