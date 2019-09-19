package com.xq.demo4restassured;

import com.jayway.restassured.RestAssured;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AssureTest {
    private static Logger logger = LoggerFactory.getLogger(AssureTest.class);

    //此主件完成测试servlet和assured设备之间的联动
//    @Autowired
//    IpConfiguration ipConfiguration;

    @Before
    public void before() {
        RestAssured.basePath = "/demo4/book";
    }

    @After
    public void after() {
    }

    @Test
    //URL为http://api.douban.com/v2/book/1220562
    //判断Json中的返回信息title
    public void testGetBook() {
        get("/book/xiaoqian").then().body("name", equalTo("xiaoqian"));
    }

//    @Test
//    //URL为http://api.douban.com/v2/book/search?q=java8
//    //判断Json中的返回信息关键字为“java8”的书本的数目
//    public void testSearchBook() {
//        given().param("q", "java8").when().get("/search").then().body("count", equalTo(2));
//    }
//
//    @Test
//    //解析JSON
//    public void testParseJson() {
//        ValidatableResponse resp = get("/1220562").then();
//        //判断返回Json数据的title
//        resp.body("title", equalTo("满月之夜白鲸现"));
//        //判断二级属性rating.max的值
//        resp.body("rating.max", equalTo(10));
//        //调用数组的方法判断数组的大小
//        resp.body("tags.size()", is(8));
//        //判断数组第一个对象的值
//        resp.body("tags[0].name", equalTo("片山恭一"));
//        //判断数组中是否有该元素
//        resp.body("author", hasItems("[日] 片山恭一"));
//    }


}