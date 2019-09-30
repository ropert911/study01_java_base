package com.study.spring.bean.sang;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by sang on 16-12-19.
 */
@Configuration
@EnableConfigurationProperties(HelloServiceProperties.class)    //告知类要支持 @ConfigurationProperties
@ConditionalOnProperty(prefix = "hello",value = "enable",matchIfMissing = true)   //指定的属性是否有指定的值，比如
@ConditionalOnClass(HelloService.class)
public class HelloServiceAutoConfiguration {
    @Autowired
    private HelloServiceProperties helloServiceProperties;
    @Bean
    public HelloService helloService() {
        HelloService helloService = new HelloService();
        helloService.setMsg(helloServiceProperties.getMsg());
        return helloService;
    }
}
