package com.rest.test.resttest.controller;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;


@RestController
public class BookController {
    private final Logger logger = Logger.getLogger(getClass());

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return "hello";
    }

    @RequestMapping(value = "/sayhello", method = RequestMethod.GET)
    public String sayHello(String name) {
        return "hello " + name;
    }

    @RequestMapping(value = "/getbook1", method = RequestMethod.GET)
    public Book book1() {
//        return new Book("三国演义", 90, "罗贯中", "花城出版社");
        return null;
    }

    @RequestMapping(value = "/getbook2", method = RequestMethod.POST)
    public Book book2(@RequestBody Book book) {
//        System.out.println(book.getName());
//        book.setPrice(33);
//        book.setAuthor("曹雪芹");
//        book.setPublisher("人民文学出版社");
        return book;
    }

    @RequestMapping(value = "/getbook3/{id}", method = RequestMethod.PUT)
    public void book3(@RequestBody Book book, @PathVariable int id) {
        logger.info("book:" + book);
        logger.info("id:" + id);
    }

    @RequestMapping(value = "/getbook4/{id}", method = RequestMethod.DELETE)
    public void book4(@PathVariable int id) {
        logger.info("id:" + id);
    }
}
