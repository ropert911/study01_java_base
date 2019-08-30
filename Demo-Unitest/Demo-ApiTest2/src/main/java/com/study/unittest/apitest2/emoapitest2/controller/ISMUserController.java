package com.study.unittest.apitest2.emoapitest2.controller;

import com.study.unittest.apitest2.emoapitest2.podo.IMSUser;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * Created by sang on 2017/9/9.
 */
@RestController
@RequestMapping("/book")
@Api(value = "", description = "book相关")
public class ISMUserController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    //添加
    @RequestMapping(value = "/book", method = RequestMethod.POST)
    public IMSUser book1(@RequestBody IMSUser book) {
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
    public IMSUser book3(@RequestBody IMSUser book, @PathVariable int id) {
        return book;
    }

    //查
    @RequestMapping(value = "/book/{name}", method = RequestMethod.GET)
    public IMSUser book4(@PathVariable String name) {
        return new IMSUser(name, 90, "罗贯中", "花城出版社");
    }
}
