package org.sang;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sang on 2017/9/9.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ConsumerBookController {
    private static Logger logger = LoggerFactory.getLogger(ConsumerBookController.class);
    private static RestTemplate restTemplate = new RestTemplate();

    @Test
    public void getHelloTest() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://127.0.0.1:8080/hello", String.class);
        logger.info("getHelloTest: getStatusCode=={}", responseEntity.getStatusCode());
        logger.info("getHelloTest: getStatusCodeValue=={}", responseEntity.getStatusCodeValue());
        logger.info("getHelloTest: getHeaders=={}", responseEntity.getHeaders());
        logger.info("getHelloTest: body=={}", responseEntity.getBody());
    }

    @Test
    public void sayHelloTest() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://127.0.0.1:8080/sayhello?name={1}", String.class, "张三");
        logger.info("sayHelloTest::responseEntity.getBody() ==== {}", responseEntity.getBody());
    }

    @Test
    public void sayHello2Test() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "李四");
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://127.0.0.1:8080/sayhello?name={name}", String.class, map);
        logger.info("sayHello2Test::responseEntity.getBody() ==== {}", responseEntity.getBody());
    }

    @Test
    public void sayHello3Test() {
        UriComponents uriComponents = UriComponentsBuilder.fromUriString("http://127.0.0.1:8080/sayhello?name={name}").build().expand("王五").encode();
        URI uri = uriComponents.toUri();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);
        logger.info("sayHello3Test::responseEntity.getBody() ==== {}", responseEntity.getBody());
    }

    @Test
    public void book1Test() {
        ResponseEntity<Book> responseEntity = restTemplate.getForEntity("http://127.0.0.1:8080/getbook1", Book.class);
        Book book = responseEntity.getBody();
        logger.info("book1Test::responseEntity.getBody() ==== {}", book);
    }

    @Test
    public void book2Test() {
        Book book = restTemplate.getForObject("http://127.0.0.1:8080/getbook1", Book.class);
        logger.info("book2Test::getForObject() ==== {}", book);
    }

    @Test
    public void book3Test() {
        Book book = new Book();
        book.setName("红楼梦");
        ResponseEntity<Book> responseEntity = restTemplate.postForEntity("http://127.0.0.1:8080/getbook2", book, Book.class);
        Book book1 = responseEntity.getBody();
        logger.info("book3Test::getForObject() ==== {}", book1);
    }

    @Test
    public void putTest() {
        Book book = new Book();
        book.setName("红楼梦");
        restTemplate.put("http://127.0.0.1:8080/getbook3/{1}", book, 99);
    }

    @Test
    public void deleteTest() {
        restTemplate.delete("http://127.0.0.1:8080/getbook4/{1}", 100);
    }
}
