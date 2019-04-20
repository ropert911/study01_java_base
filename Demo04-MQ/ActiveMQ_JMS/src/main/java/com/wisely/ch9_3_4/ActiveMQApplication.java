package com.wisely.ch9_3_4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.core.JmsTemplate;

@SpringBootApplication
public class ActiveMQApplication implements CommandLineRunner{ //1 提供commandline接口，在程序启动后执行的代码
	
	@Autowired
	JmsTemplate jmsTemplate; //2

    public static void main(String[] args) {
        SpringApplication.run(ActiveMQApplication.class, args);
        
    }

	@Override
	public void run(String... args) throws Exception {
		jmsTemplate.send("my-destination", new Msg()); //3
		
	}
}
