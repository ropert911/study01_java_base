package com.study.unittest.apitest2.emoapitest2.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest //(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ISMUserController4Test extends AbstractTestNGSpringContextTests{
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @BeforeTest
    public void BeforeTest() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

    }


    @Test
    public void testBook1() throws Exception {
        String result = mockMvc.perform(get("/book/hello1"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("hello")))
                .andReturn().getResponse().getContentAsString();
        logger.info(result);
        Assert.assertNotNull(result);
    }
}