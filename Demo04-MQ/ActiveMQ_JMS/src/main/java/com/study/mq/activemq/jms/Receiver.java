package com.study.mq.activemq.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

	@JmsListener(destination = "my-destination") //1 相当于toppic
	public void receiveMessage(String message) {
		System.out.println("接受到: <" + message + ">");
	}

}
