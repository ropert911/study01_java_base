package com.study.redis;

import com.study.redis.example2.Reids1Transaction;
import com.study.redis.example2.Reids2PipeLine;
import com.study.redis.example2.Reids3AutoComp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class example2Tests {

    @Autowired
    Reids1Transaction reidsTransaction;

    @Autowired
    Reids2PipeLine reids2Transaction;

    @Autowired
    Reids3AutoComp reids3AutoComp;

    @Test
    public void contextLoads() {
//        reidsTransaction.test();
//        reidsTransaction.test2();

//        reids2Transaction.test1();
//        reids2Transaction.test2();

        reids3AutoComp.test();
    }
}
