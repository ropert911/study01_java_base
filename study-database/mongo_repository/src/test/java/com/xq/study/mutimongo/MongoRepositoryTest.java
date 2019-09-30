package com.xq.study.mutimongo;

import com.xq.study.mutimongo.po.PrimaryMongoObject;
import com.xq.study.mutimongo.repository.primary.PrimaryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by neo on 2017/5/6.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoRepositoryTest {
    private static final Logger logger = LoggerFactory.getLogger(MongoRepositoryTest.class);

    @Autowired
    private PrimaryRepository primaryRepository;

    @Test
    public void TestSave() {
        logger.info("************************************************************");
        logger.info("测试开始");
        logger.info("************************************************************");

        PrimaryMongoObject p1 = new PrimaryMongoObject();
        p1.setValue("第一个库的对象");
        this.primaryRepository.save(p1);


        List<PrimaryMongoObject> primaries = this.primaryRepository.findAll();
        for (PrimaryMongoObject primary : primaries) {
            logger.info(primary.toString());
        }

        primaryRepository.deleteAll();


        logger.info("************************************************************");
        logger.info("测试完成");
        logger.info("************************************************************");
    }
}
