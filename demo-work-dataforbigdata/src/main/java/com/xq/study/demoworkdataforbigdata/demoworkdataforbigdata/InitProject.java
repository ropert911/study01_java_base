package com.xq.study.demoworkdataforbigdata.demoworkdataforbigdata;

import com.alibaba.fastjson.JSON;
import com.xq.study.demoworkdataforbigdata.demoworkdataforbigdata.dto.GWSensorDataDTO;
import com.xq.study.demoworkdataforbigdata.demoworkdataforbigdata.dto.UnwindData;
import com.xq.study.demoworkdataforbigdata.demoworkdataforbigdata.utils.TimeUtilForJavaEight;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author sk-qianxiao
 * @date 2019/11/6
 */
@Component
public class InitProject implements ApplicationRunner {
    private static final Logger logger = LoggerFactory.getLogger(InitProject.class);


    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("==========init project===========");

        List<UnwindData> unwindDataList = new ArrayList<>();
        unwindDataList.add(UnwindData.builder().key("sensorVoltage").value(5.5).build());
        unwindDataList.add(UnwindData.builder().key("temperature").value(100.2).build());

        String formatDate = TimeUtilForJavaEight.getFormatDate(System.currentTimeMillis(), TimeUnit.MILLISECONDS, TimeUtilForJavaEight.PATTERN_YYYY_MM_DDTHH_MM_SS_XXX);
        GWSensorDataDTO gwSensorDataDTO = GWSensorDataDTO.builder()
                .rssi(-27.0f).gwip("10.10.21.84").data("a037011464").channel(488500000).snr_max(37.0f)
                .frameCnt(4).sf(12).systype(3).fport(5).snr(21.3f).snr_min(9.5f).repeater("00000000ffffffff")
                .itfTime(System.currentTimeMillis() / 1_000).time(formatDate).sysTime(formatDate)
                .gwid("000080029c09e987")
                .deviceTypeCode("3001").macAddr("9010000500001761")
                .unwindData(unwindDataList)
                .build();

        String valueUser = JSON.toJSONString(gwSensorDataDTO);

        System.out.println("====================================1111111111111111");
        System.out.println(valueUser);
        System.out.println("====================================2222222222222222");
//        kafkaOperations.sendData(originData, (deviceTypeCode + "_" + macAddr + "_" + gwid).getBytes(), v.toJSONString().getBytes());
    }
}