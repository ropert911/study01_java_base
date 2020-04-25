package testNG;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import testNG.controller.AUserController;
import testNG.podo.AUser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JUnit1ControllerTest {
    private static Logger logger = LoggerFactory.getLogger(JUnit1ControllerTest.class);

    @Autowired
    private AUserController aUserController;

    //伪造mvc环境
    private MockMvc mockMvc;


    @Before
    public void setUp() throws Exception {
        //两个都可以，一个是全部的，一个是单个
        mockMvc = MockMvcBuilders.standaloneSetup(aUserController).build();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void hello1() throws Exception {
        String result = mockMvc.perform(get("/book/hello1"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("hello")))
                .andReturn().getResponse().getContentAsString();
        logger.info(result);
    }

    @Test
    public void book2() throws Exception {
        logger.info("xxxxxxxxxxxx-----book2");
        String result = mockMvc.perform(delete("/book/book/" + "xiaoqian"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        logger.info(result);
    }

    @Test
    public void book3() throws Exception {
        logger.info("xxxxxxxxxxxx-----book3");
        String result = mockMvc.perform(get("/book/book/" + "xiaoqian")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString(); //对返回字符串的json内容进行判断
        logger.info(result);
    }

    @Test
    public void book4() throws Exception {
        logger.info("xxxxxxxxxxxx-----book4");
        AUser book = new AUser();
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
        logger.info("xxxxxxxxxxxx-----book4-2");
    }
}