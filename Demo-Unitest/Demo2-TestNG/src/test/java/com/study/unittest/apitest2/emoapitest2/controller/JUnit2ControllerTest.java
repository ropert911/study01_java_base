package com.study.unittest.apitest2.emoapitest2.controller;

import com.study.unittest.apitest2.emoapitest2.podo.IMSUser;
import org.junit.*;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sang on 2017/9/9.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JUnit2ControllerTest {
    private static Logger logger = LoggerFactory.getLogger(JUnit2ControllerTest.class);

    @BeforeClass
    public static void beforclasss() {
        logger.info("step before class *************");

    }

    @AfterClass
    public static void afterclass() {
        logger.info("step after class *************");
    }

    @Before
    public void beforTest() {
        logger.info("step before test **************");
    }

    @After
    public void afterTest() {
        logger.info("step after test **************");
    }


    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void bookAdd() throws Exception {
        IMSUser book = new IMSUser();
        book.setName("红楼梦");
        book.setAuthor("xq");
        book.setPrice(30);
        book.setPublisher("人民文学出版本社");


        ResponseEntity<IMSUser> response = testRestTemplate.postForEntity("/book/book", book, IMSUser.class);
        IMSUser book1 = testRestTemplate.postForObject("/book/book", book, IMSUser.class);
        logger.info(response.getBody().toString());
        logger.info(book1.toString());
    }

    @Test
    public void bookDel() throws Exception {
        testRestTemplate.delete("/book/book/" + "xiaoqian");
    }

    @Test
    public void bookGet() throws Exception {
        String result = testRestTemplate.getForObject("/book/book/" + "xiaoqian", String.class);
        logger.info(result);
    }

    @Test
    public void bookModify() throws Exception {
        IMSUser book = new IMSUser();
        book.setName("红楼梦");
        book.setAuthor("xq");
        book.setPrice(30);
        book.setPublisher("人民文学出版本社");

        IMSUser b = testRestTemplate.postForObject("/book/book/" + "xiaoqian", book, IMSUser.class);
        logger.info(b.toString());
    }
}
