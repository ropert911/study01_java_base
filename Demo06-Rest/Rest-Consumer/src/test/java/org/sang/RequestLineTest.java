package org.sang;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.sang.requestLine.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RequestLineTest {
    private static Logger logger = LoggerFactory.getLogger(ConsumerBookController.class);
    @Autowired
    private static PmRemoteService remoteService;


    @Test
    public void queryAllUserDeviveListTest() {
        String token = "";
        String lang = "";
        int pageIndex = 1;
        // 默认最大查询5000个AC, 后面如果ac太多需要考虑在ui翻页
        int pageSize = 5000;
        org.sang.requestLine.ResponseInfo<CustomAcInfoList> responseInfo = remoteService.getAcListByUser(token, lang, pageIndex, pageSize);
    }
}
