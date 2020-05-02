package com.study.rest.consumer.event;

import com.study.rest.consumer.event.podo.CustomAcInfoList;
import com.study.rest.consumer.event.requestLine.MyBookService;
import com.study.rest.consumer.event.requestLine.PMService;
import com.study.rest.consumer.event.response.ResponseInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
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
