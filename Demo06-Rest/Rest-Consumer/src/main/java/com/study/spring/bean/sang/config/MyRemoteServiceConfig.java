package com.study.spring.bean.sang.config;

import feign.Feign;
import feign.Request;
import feign.Retryer;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import com.study.spring.bean.sang.requestLine.MyBookService;
import com.study.spring.bean.sang.requestLine.PMService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by sk-qianxiao on 2019/5/20.
 */
@Configuration
public class MyRemoteServiceConfig {
    @Bean
    MyBookService pmRemoteService() {
        return Feign.builder()
//                .encoder(new JacksonEncoder())
//                .decoder(new JacksonDecoder())
                .options(new Request.Options(2000, 3500))
                .retryer(Retryer.NEVER_RETRY)
                .target(MyBookService.class, "http://127.0.0.1:8080");
    }


    @Bean
    PMService pMServiceService() {
        return Feign.builder()
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .options(new Request.Options(2000, 3500))
                .retryer(Retryer.NEVER_RETRY)
                .target(PMService.class, "http://192.168.20.51:17004");
    }
}
