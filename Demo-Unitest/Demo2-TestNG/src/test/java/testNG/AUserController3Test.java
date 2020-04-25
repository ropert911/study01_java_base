package testNG;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.*;
import org.testng.Assert;
import testNG.controller.AUserController;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class AUserController3Test extends AbstractTestNGSpringContextTests {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AUserController aUserController;

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @BeforeClass   //所有方法执行前执行一次
    public void BeforeClass() {
        //两个都可以，一个是全部的，一个是单个
//        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        mockMvc = MockMvcBuilders.standaloneSetup(aUserController).build();
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

    @Test
    public void testBook2() throws Exception {
        String result = mockMvc.perform(get("/book/hello1"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("hello")))
                .andReturn().getResponse().getContentAsString();
        logger.info(result);
        Assert.assertNotNull(result);
    }
}