package com.xq.demo4restassured;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JsonTest {
    private static Logger logger = LoggerFactory.getLogger(JsonTest.class);

    @Before
    public void before() {
        RestAssured.basePath = "/demo4/book";
    }

    //简单解析Jason
    @Test
    public void jsonParse1() {
        RestAssured.get("/book/xiaoqian").then().body("name", equalTo("xiaoqian"));
        RestAssured.get("/book/xiaoqian").then().body("price", equalTo(90));
        RestAssured.get("/book/xiaoqian").then().body("tags", hasItems("小说","娱乐"));
    }


    //解析JSON
    @Test
    public void jsonParse2() {
        ValidatableResponse resp = RestAssured.get("/book/xiaoqian").then();
        //判断返回Json数据的title
        resp.body("name", equalTo("xiaoqian"));
        //判断二级属性rating.max的值
        resp.body("publisher.name", equalTo("人民文学出版社"));
        //调用数组的方法判断数组的大小
        resp.body("authors.size()", equalTo(2));
        //判断数组第一个对象的值
        resp.body("authors[0].name", equalTo("张三"));
        //判断数组中是否有该元素
        resp.body("tags", hasItems("小说"));
    }
}