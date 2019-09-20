package com.study.unittest.apitest.demoapitest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

//监控获取Web初始化事件
@Component
public class IpConfiguration implements ApplicationListener<WebServerInitializedEvent> {
    private static Logger logger = LoggerFactory.getLogger(IpConfiguration.class);
    private int port;

    @Override
    public void onApplicationEvent(WebServerInitializedEvent webServerInitializedEvent) {
        this.port = webServerInitializedEvent.getWebServer().getPort();
        logger.info("=================== {}", port);

    }
}
