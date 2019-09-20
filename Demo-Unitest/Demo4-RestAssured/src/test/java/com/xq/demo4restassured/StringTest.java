package com.xq.demo4restassured;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.get;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StringTest {
    private static Logger logger = LoggerFactory.getLogger(StringTest.class);

    @Before
    public void before() {
        RestAssured.basePath = "/demo4/string";
    }

    //无参数
    @Test
    public void parmTest1() {
        String value = get("/hello1").getBody().print();
        assertTrue(value.equals("hello"));
        get("/hello1").then().assertThat().body(equalTo("hello"));

    }


    //url本就就是参数
    @Test
    public void parmTest2() {
        String value = get("/hello2/xiaoqian").getBody().print();
        assertTrue(value.equals("hello xiaoqian"));
    }

    //url中指定参数
    @Test
    public void parmTest3() {
        String value = RestAssured.given().param("name", "xiaomin").when().get("/hello3").getBody().print();
        assertTrue(value.equals("hello xiaomin"));
    }
}