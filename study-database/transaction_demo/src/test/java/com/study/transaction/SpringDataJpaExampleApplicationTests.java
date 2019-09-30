package com.study.transaction;

import com.study.transaction.domain.User;
import com.study.transaction.service.DemoServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringDataJpaExampleApplicationTests {
	@Autowired
	private DemoServiceImpl userService;

	@Test
	public void contextLoads() {
		User user = new User();
		user.setId(100);
		user.setName("100");
		user.setAddress("100");
		userService.savePersonWithRollBack(user);
        user.setId(101);
        user.setName("101");
        user.setAddress("101");
        userService.savePersonWithoutRollBack(user);

        user = new User();
        user.setId(103);
        user.setName("xq");
        user.setAddress("103");
        userService.savePersonWithRollBack(user);
//        userService.savePersonWithoutRollBack(user);
	}

}
