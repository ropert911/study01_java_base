package com.study.spring.bean.sang;

import org.junit.Test;
import org.junit.runner.RunWith;
import com.study.spring.bean.sang.podo.CustomAcInfoList;
import com.study.spring.bean.sang.requestLine.*;
import com.study.spring.bean.sang.response.ResponseInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RequestLineTest {
    private static Logger logger = LoggerFactory.getLogger(BookTest.class);
    @Autowired
    private MyBookService remoteService;
    @Autowired
    private PMService pmService;


    @Test
    public void sayHelloTest() {
        String info = remoteService.sayHello("xiaoqian");
        logger.info(info);
    }

    @Test
    public void quertygetAcListByUserTest() {
        String token = "pltd4dnfqwqg3sv8flreyt5sff3uutdz";  //使用时要换成可用的tokent
        String lang = "";
        int pageIndex = 1;
        int pageSize = 5000;
        ResponseInfo<CustomAcInfoList> list = pmService.getAcListByUser(token, lang, pageIndex, pageSize);
        logger.info("aaaaaaaaaaaaaaaaaaaaa" + list.toString());
    }
}
