package com.xq.study.demoworkdataforbigdata.demoworkdataforbigdata;

import com.alibaba.fastjson.JSON;
import com.xq.study.demoworkdataforbigdata.demoworkdataforbigdata.dto.GWSensorDataDTO;
import com.xq.study.demoworkdataforbigdata.demoworkdataforbigdata.dto.UnwindData;
import com.xq.study.demoworkdataforbigdata.demoworkdataforbigdata.kafka.KafkaOperations;
import com.xq.study.demoworkdataforbigdata.demoworkdataforbigdata.utils.TimeUtilForJavaEight;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

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
        logger.info("=====================Begin........{}", originData);
        Random random = new Random();
        int max = 105;
        for (int i = 0; i < max; ++i) {
            String deviceTypeCode = "3001";
            String devMacAddr = "90100005000" + Integer.toHexString(0x11760 + i).toUpperCase();
            String gwid = "000080029c09e987";
            List<UnwindData> unwindDataList = new ArrayList<>();
            unwindDataList.add(UnwindData.builder().key("sensorVoltage").value(5.5).build());

            int step = (max - 3) / 5;
            if (i < step * 5) {
                unwindDataList.add(UnwindData.builder().key("temperature").value(30 + random.nextInt(3 * i)).build());
            } else {
                unwindDataList.add(UnwindData.builder().key("temperature").value(50 + random.nextInt(3 * 5)).build());
            }


            String formatDate = TimeUtilForJavaEight.getFormatDate(System.currentTimeMillis(), TimeUnit.MILLISECONDS, TimeUtilForJavaEight.PATTERN_YYYY_MM_DDTHH_MM_SS_XXX);
            String sysTime = TimeUtilForJavaEight.getFormatDate(System.currentTimeMillis(), TimeUnit.MILLISECONDS, TimeUtilForJavaEight.PATTERN_YYYY_MM_DDTHH_MM_SS_SSSXXX);

            GWSensorDataDTO gwSensorDataDTO = GWSensorDataDTO.builder()
                    .rssi(-27.0f).gwip("10.10.21.84").data("a037011464").channel(488500000).snr_max(37.0f)
                    .frameCnt(4).sf(12).systype(3).fport(5).snr(21.3f).snr_min(9.5f).repeater("00000000ffffffff")
                    .itfTime(System.currentTimeMillis() / 1_000).time(formatDate).sysTime(sysTime)
                    .gwid(gwid)
                    .deviceTypeCode(deviceTypeCode).macAddr(devMacAddr)
                    .unwindData(unwindDataList)
                    .build();


            kafkaOperations.sendData(originData, (deviceTypeCode + "_" + devMacAddr + "_" + gwid).getBytes(), JSON.toJSONString(gwSensorDataDTO).getBytes());
        }
        logger.info("=====================End........");
    }
}