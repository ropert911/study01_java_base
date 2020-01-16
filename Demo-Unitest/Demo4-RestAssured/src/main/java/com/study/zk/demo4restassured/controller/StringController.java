package com.study.zk.demo4restassured.controller;

import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * Created by sang on 2017/9/9.
 */
@RestController
@RequestMapping("/string")
@Api(value = "", description = "book相关")
public class StringController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

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
}
