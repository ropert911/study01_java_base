package com.study.cache.cacheable.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CacheApplicationTests {
	@Autowired
	Teacher student;
	@Test
	public void memCachedTest() {
		System.out.println(student.getAge("name1"));
		System.out.println(student.getAge("name2"));
		System.out.println(student.getAge("name3"));
        System.out.println(student.test());
		System.out.println("=========================");
		System.out.println(student.getAge("name1"));
		System.out.println(student.getAge("name2"));
		System.out.println(student.getAge("name3"));
        System.out.println(student.test());
		System.out.println("=========================remove name2");
		student.remove("name2");
		System.out.println(student.getAge("name1"));
		System.out.println(student.getAge("name2"));
		System.out.println(student.getAge("name3"));
        System.out.println(student.test());
		System.out.println("========================= update name3");
		student.updateAge("name3");
		System.out.println(student.getAge("name1"));
		System.out.println(student.getAge("name2"));
		System.out.println(student.getAge("name3"));
        System.out.println(student.test());
		System.out.println("========================= after 5 s ");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(student.getAge("name1"));
		System.out.println(student.getAge("name2"));
		System.out.println(student.getAge("name3"));
        System.out.println(student.test());
	}

}
