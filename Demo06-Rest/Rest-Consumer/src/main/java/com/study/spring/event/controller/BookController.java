package com.study.spring.event.controller;

import com.study.spring.event.podo.Book;
import io.swagger.annotations.Api;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

/**
 * Created by sang on 2017/9/9.
 */
@RestController
@RequestMapping("/book")
@Api(value = "", description = "book相关")
public class BookController {
    private final Logger logger = Logger.getLogger(getClass());

    @RequestMapping(value = "/hello1", method = RequestMethod.GET)
    public String hello1() {
        return "hello";
    }

    @RequestMapping(value = "/hello2/{name}", method = RequestMethod.GET)
    public String hello2(@PathVariable String name) {
        return "hello " + name;
    }

    @RequestMapping(value = "/hello3", method = RequestMethod.GET)
    public String hello3(String name) {
        return "hello " + name;
    }

    //添加
    @RequestMapping(value = "/book", method = RequestMethod.POST)
    public Book book1(@RequestBody Book book) {
        System.out.println(book.getName());
        book.setPrice(33);
        book.setAuthor("曹雪芹");
        book.setPublisher("人民文学出版社");
        return book;
    }

    //删除
    @RequestMapping(value = "/book/{name}", method = RequestMethod.DELETE)
    public String book2(@PathVariable String name) {
        return "delete book：" + name;
    }

    //改
    @RequestMapping(value = "/book/{id}", method = RequestMethod.PUT)
    public Book book3(@RequestBody Book book, @PathVariable int id) {
        return book;
    }

    //查
    @RequestMapping(value = "/book/{name}", method = RequestMethod.GET)
    public Book book4(@PathVariable String name) {
        return new Book(name, 90, "罗贯中", "花城出版社");
    }
}
