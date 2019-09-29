package org.xx;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.sang.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UseMyStarterApplicationTests {

	@Autowired
	private HelloService helloService;

	@Test
	public void contextLoads() {
		System.out.println( helloService.sayHello());
	}

}
