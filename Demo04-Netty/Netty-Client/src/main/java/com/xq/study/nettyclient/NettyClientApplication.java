package com.xq.study.nettyclient;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class NettyClientApplication {

	public static void main(String[] args) {
		//SpringApplication.run(SpbNettyApplication.class, args);
		new SpringApplicationBuilder(NettyClientApplication.class).web(false).run(args);
	}
}
