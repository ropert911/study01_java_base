package com.xq.study.anocation;

import com.xq.study.anocation.fruitanocation.Apple;
import com.xq.study.anocation.fruitanocation.FruitInfoUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AnocationApplicationTests {
    private static final Logger LOGGER = LoggerFactory.getLogger(FruitInfoUtil.class);

    @Autowired
    Apple apple;

    @Test
    public void contextLoads() {
        FruitInfoUtil.getFruitInfo(Apple.class, apple);
        LOGGER.error("===============" + apple.getAppleName());
        LOGGER.error("===============" + apple.getAppleColor());
        LOGGER.error("===============" + apple.getAppleProvider());
        LOGGER.error("===============" + apple);
    }
}
