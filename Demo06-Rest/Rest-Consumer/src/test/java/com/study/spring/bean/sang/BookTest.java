package com.study.spring.bean.sang;

import org.junit.Test;
import org.junit.runner.RunWith;
import com.study.spring.bean.sang.podo.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/**
 * Created by sang on 2017/9/9.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BookTest {
    private static Logger logger = LoggerFactory.getLogger(BookTest.class);
    private static RestTemplate restTemplate = new RestTemplate();

    @Test
    public void getHelloTest() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://127.0.0.1:8080/book/hello1", String.class);
        logger.info("getHelloTest: getStatusCode=={}", responseEntity.getStatusCode());
        logger.info("getHelloTest: getStatusCodeValue=={}", responseEntity.getStatusCodeValue());
        logger.info("getHelloTest: getHeaders=={}", responseEntity.getHeaders());
        logger.info("getHelloTest: body=={}", responseEntity.getBody());

        responseEntity = restTemplate.getForEntity("http://127.0.0.1:8080/book/hello2/{name}", String.class, "xiaoqian");
        logger.info("222222: body=={}", responseEntity.getBody());

        responseEntity = restTemplate.getForEntity("http://127.0.0.1:8080/book/hello3?name={name}", String.class, "xiaoqian");
        logger.info("333333: body=={}", responseEntity.getBody());

        UriComponents uriComponents = UriComponentsBuilder.fromUriString("http://127.0.0.1:8080/book/hello2/{name}").build().expand("王五").encode();
        URI uri = uriComponents.toUri();
       responseEntity = restTemplate.getForEntity(uri, String.class);
        logger.info("4444444444 ==== {}", responseEntity.getBody());
    }


    @Test
    public void bookAdd() {
        Book book = new Book();
        book.setName("红楼梦");
        book.setAuthor("xq");
        book.setPrice(30);
        book.setPublisher("人民文学出版本社");
        ResponseEntity<Book> responseEntity = restTemplate.postForEntity("http://127.0.0.1:8080/book/book", book, Book.class);
        logger.info("bookAdd ==== {}", responseEntity.getBody());
    }

    @Test
    public void bookDel() {
        restTemplate.delete("http://127.0.0.1:8080/book/book/{name}", "xiaoqian");
    }

    @Test
    public void bookGet() {
        ResponseEntity<Book> responseEntity = restTemplate.getForEntity("http://127.0.0.1:8080/book/book/{name}", Book.class, "xiaoqian");
        logger.info("sayHello2Test::responseEntity.getBody() ==== {}", responseEntity.getBody());
    }

    @Test
    public void bookModify() {
        Book book = new Book();
        book.setName("红楼梦");
        book.setAuthor("xq");
        book.setPrice(30);
        book.setPublisher("人民文学出版本社");
        restTemplate.put("http://127.0.0.1:8080/book/book/{id}",book,1);
    }

//    @Test
//    public void sayHello3Test() {
//        UriComponents uriComponents = UriComponentsBuilder.fromUriString("http://127.0.0.1:8080/sayhello?name={name}").build().expand("王五").encode();
//        URI uri = uriComponents.toUri();
//        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);
//        logger.info("sayHello3Test::responseEntity.getBody() ==== {}", responseEntity.getBody());
//    }

}
