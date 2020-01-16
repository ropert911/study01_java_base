package com.study.gpb.consumer;


import com.google.protobuf.InvalidProtocolBufferException;
import com.skspruce.common.protobuf.Sksinfo;
import com.skspruce.common.protobuf.Sksinfo.SksInfo;
import com.study.gpb.service.AcGpbService;
import com.study.gpb.service.ApGpbService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.utils.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 批量消费Kafka 队列中的数据。
 * 处理过程中无错误抛出才会更新offset
 * <p>
 *
 * @author sk-ziconglu on 2017/5/4.
 */
@Component
public class SksInfoConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(SksInfoConsumer.class);

    @Autowired
    private AcGpbService acGpbService;

    @Autowired
    private ApGpbService apGpbService;
    /**
     * Receive AP data {@link ConsumerRecord} from kafka's topic partitions {@Link TopicPartition }.
     *
     * @param list
     */
    @KafkaListener(group = "${spring.kafka.consumer.group-id}",
            topics = "${sks.pm.data.topic.base}",
            containerFactory = "kafkaListenerContainerFactory")
    public void onAPMessage(List<Bytes> list, Acknowledgment ack) {
        try {
            list.forEach(record -> {
                // decode ac data from google protocol buffer
                try {
                    SksInfo sksInfo = SksInfo.parseFrom(record.get());
                    Sksinfo.SRC src = sksInfo.getSrc();
                    if (Sksinfo.SRC.AC_T == src) {
                        acGpbService.makeAcInfoForGpb(sksInfo.getAc());
                    }
                    if (Sksinfo.SRC.AP_T == src) {
                        LOGGER.warn("receive ap info ..............");
                        apGpbService.makeApInfoForGpb(sksInfo.getAp(),0,0);
                    } else {
                        LOGGER.warn("Sksinfo.SRC = {}", src);
                    }
                } catch (InvalidProtocolBufferException e) {
                    LOGGER.warn("Invalid Protobuf data. {}", e.getMessage());
                    return; // continue forEach
                }
            });
        } catch (Exception e) {
            LOGGER.error("Process base Bytes failed.", e);
        }
        ack.acknowledge();
    }
}
