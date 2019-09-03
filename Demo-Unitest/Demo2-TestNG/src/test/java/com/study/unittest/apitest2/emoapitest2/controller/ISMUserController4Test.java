package com.study.unittest.apitest2.emoapitest2.controller;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.testng.Assert;
import org.testng.annotations.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ISMUserController4Test {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void testBook1() {
        logger.info("3333---- testBook1");
        String result = testRestTemplate.getForObject("/book/hello1", String.class);
        logger.info(result);
        Assert.assertNotNull(result);
    }
}