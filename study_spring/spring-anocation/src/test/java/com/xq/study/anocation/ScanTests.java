package com.xq.study.anocation;

import com.xq.study.anocation.scan.ScanClass1;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScanTests {

	@Test
	public void contextLoads() {
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
		annotationConfigApplicationContext.register(ScanClass1.class);
		annotationConfigApplicationContext.refresh();
		ScanClass1 injectClass = annotationConfigApplicationContext.getBean(ScanClass1.class);
		injectClass.print();
	}

}
