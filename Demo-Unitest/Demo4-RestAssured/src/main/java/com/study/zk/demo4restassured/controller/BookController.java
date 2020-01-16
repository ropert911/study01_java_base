package com.study.zk.demo4restassured.controller;

import com.study.zk.demo4restassured.podo.Author;
import com.study.zk.demo4restassured.podo.Book;
import com.study.zk.demo4restassured.podo.Publisher;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sang on 2017/9/9.
 */
@RestController
@RequestMapping("/book")
@Api(value = "", description = "book相关")
public class BookController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 添加
     *
     * @param book xx
     * @return xx
     */
    @RequestMapping(value = "/book", method = RequestMethod.POST)
    public Book book1(@RequestBody Book book) {
        return book;
    }


    /**
     * 删除
     *
     * @param name
     * @return
     */
    @RequestMapping(value = "/book/{name}", method = RequestMethod.DELETE)
    public String book2(@PathVariable String name) {
        return "delete book：" + name;
    }

    /**
     * 改
     *
     * @param book
     * @param name
     * @return
     */
    @RequestMapping(value = "/book/{name}", method = RequestMethod.PUT)
    public Book book3(@RequestBody Book book, @PathVariable String name) {
        book.setName(name);
        return book;
    }

    /**
     * 查
     *
     * @param name
     * @return
     */
    @RequestMapping(value = "/book/{name}", method = RequestMethod.GET)
    public Book book4(@PathVariable String name) {
        Publisher publisher = new Publisher();
        publisher.setName("人民文学出版社");
        List<Author> auths = new ArrayList<>();
        auths.add(new Author("张三"));
        auths.add(new Author("李四"));
        List<String> tags = new ArrayList<>();
        tags.add("娱乐");
        tags.add("小说");
        return new Book(name, 90, 89.5d, auths, publisher, tags);
    }




    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public Publisher hello4(String name) {
        Publisher publisher = new Publisher();
        publisher.setName("XXX");
        return publisher;
    }
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Integer> hello5(String name) {
        List<Integer> a = new ArrayList<>();
        a.add(1);
        a.add(2);
        a.add(3);
        return a;
    }
}
