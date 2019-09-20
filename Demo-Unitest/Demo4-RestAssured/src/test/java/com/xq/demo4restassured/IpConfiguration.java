package com.xq.demo4restassured;

import io.restassured.RestAssured;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

//此主件完成测试servlet和assured设备之间的联动
@Component
public class IpConfiguration implements ApplicationListener<WebServerInitializedEvent> {
    private static Logger logger = LoggerFactory.getLogger(IpConfiguration.class);
    private int port;

    @Override
    public void onApplicationEvent(WebServerInitializedEvent webServerInitializedEvent) {
        this.port = webServerInitializedEvent.getWebServer().getPort();
        RestAssured.baseURI = "http://localhost:" + port;
        RestAssured.port = port;
        logger.info("=================== {}", port);

    }
}
