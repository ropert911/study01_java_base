package com.study.zk.demo4restassured;

import io.restassured.RestAssured;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.get;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AssureTest {
    private static Logger logger = LoggerFactory.getLogger(AssureTest.class);

    //    无参数
    @Test
    public void parmTest1() {
        RestAssured.basePath = "/demo4/string";
        String value = get("/hello1").getBody().print();
        assertTrue(value.equals("hello"));
    }


    //url本就就是参数
    @Test
    public void parmTest2() {
        RestAssured.basePath = "/demo4/string";
        String value = get("/hello2/xiaoqian").getBody().print();
        assertTrue(value.equals("hello xiaoqian"));
    }

    //url中指定参数
    @Test
    public void parmTest3() {
        RestAssured.basePath = "/demo4/string";
        String value = RestAssured.given().param("name", "xiaomin").when().get("/hello3").getBody().print();
        assertTrue(value.equals("hello xiaomin"));
    }

    //查询方式：Get
    @Test
    public void methodTestGet() {
        RestAssured.basePath = "/demo4/book";
        get("/book/xiaoqian").then().body("name", equalTo("xiaoqian"));
        get("/book/xiaoqian").then().body("price", equalTo(90));
    }
}