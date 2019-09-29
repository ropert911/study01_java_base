package com.xq.study.aop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpbAopApplicationTests {
	@Autowired
	Performance per;

	@Test
	public void contextLoads() {
		per.performance(1);
	}

}
