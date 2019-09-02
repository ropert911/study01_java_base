package com.study.unittest.apitest.demoapitest.controller;

import com.study.unittest.apitest.demoapitest.podo.Book;
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
public class Book2ControllerTest {
    private static Logger logger = LoggerFactory.getLogger(Book2ControllerTest.class);

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
    public void getHelloTest() throws Exception {
        String result = testRestTemplate.getForObject("/book/hello1", String.class);
        logger.info(result);

        result = testRestTemplate.getForObject("/book/hello2/" + "xiaoqian", String.class);
        logger.info(result);
        Assert.assertEquals(result, "hello xiaoqian");


        result = testRestTemplate.getForObject("/book/hello3?name={1}", String.class, "xiaoqian2");
        Assert.assertEquals(result, "hello xiaoqian2");
        logger.info(result);

        Map param = new HashMap<String, String>();
        param.put("name", "xiaoqian2");
        result = testRestTemplate.getForObject("/book/hello3?name={name}", String.class, param);
        Assert.assertEquals(result, "hello xiaoqian2");
        logger.info(result);
    }


    @Test
    public void getHello1Test() throws Exception {
        Assert.assertEquals("ffffffff", "hello xiaoqian2");
    }

    @Test
    public void bookAdd() throws Exception {
        Book book = new Book();
        book.setName("红楼梦");
        book.setAuthor("xq");
        book.setPrice(30);
        book.setPublisher("人民文学出版本社");


        ResponseEntity<Book> response = testRestTemplate.postForEntity("/book/book", book, Book.class);
        Book book1 = testRestTemplate.postForObject("/book/book", book, Book.class);
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
        Book book = new Book();
        book.setName("红楼梦");
        book.setAuthor("xq");
        book.setPrice(30);
        book.setPublisher("人民文学出版本社");

        Book b = testRestTemplate.postForObject("/book/book/" + "xiaoqian", book, Book.class);
        logger.info(b.toString());
    }
}
