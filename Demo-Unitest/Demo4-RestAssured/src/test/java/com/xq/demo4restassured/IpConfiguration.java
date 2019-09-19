package com.xq.demo4restassured;

import com.jayway.restassured.RestAssured;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class IpConfiguration implements ApplicationListener<WebServerInitializedEvent> {
    private static Logger logger = LoggerFactory.getLogger(IpConfiguration.class);
    private int port;

    @Override
    public void onApplicationEvent(WebServerInitializedEvent webServerInitializedEvent) {
        this.port = webServerInitializedEvent.getWebServer().getPort();
        RestAssured.baseURI = "http://127.0.0.1:" + port;
        RestAssured.port = port;
        logger.info("=================== {}", port);

    }
}
