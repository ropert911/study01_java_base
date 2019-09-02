package com.study.unittest.apitest2.emoapitest2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.study.unittest.apitest2.emoapitest2.controller.ISMUserController;
import com.study.unittest.apitest2.emoapitest2.podo.IMSUser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by sang on 2017/9/9.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class IMSUserTest {
    private static Logger logger = LoggerFactory.getLogger(IMSUserTest.class);

    // 注入Spring 工厂
    @Autowired
    private WebApplicationContext wac;
    //伪造mvc环境
    private MockMvc mockMvc;

    @Before
    public void setup() {
        //两个都可以，一个是全部的，一个是单个
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        mockMvc = MockMvcBuilders.standaloneSetup(new ISMUserController()).build();
    }

    @Test
    public void bookAdd() throws Exception {
        IMSUser book = new IMSUser();
        book.setName("红楼梦");
        book.setAuthor("xq");
        book.setPrice(30);
        book.setPublisher("人民文学出版本社");
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(book);

        String result = mockMvc.perform(post("/book/book")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").isString())
                .andReturn().getResponse().getContentAsString();
        logger.info(result);
    }

    @Test
    public void bookDel() throws Exception {
        String result = mockMvc.perform(delete("/book/book/" + "xiaoqian"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        logger.info(result);
    }

    @Test
    public void bookGet() throws Exception {
        String result = mockMvc.perform(get("/book/book/" + "xiaoqian")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString(); //对返回字符串的json内容进行判断
        logger.info(result);
    }

    @Test
    public void bookModify() throws Exception {
        IMSUser book = new IMSUser();
        book.setName("红楼梦");
        book.setAuthor("xq");
        book.setPrice(30);
        book.setPublisher("人民文学出版本社");
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(book);

        String result = mockMvc.perform(get("/book/book/" + "xiaoqian")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString(); //对返回字符串的json内容进行判断
        logger.info(result);
    }
}
