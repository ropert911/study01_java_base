package org.sang.requestLine;

import feign.Feign;
import feign.Request;
import feign.Retryer;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.sang.requestLine.PmRemoteService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by sk-qianxiao on 2019/5/20.
 */
@Configuration
public class RemotConfig {
    @Bean
    PmRemoteService pmRemoteService() {
        return Feign.builder()
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .options(new Request.Options(2000, 3500))
                .retryer(Retryer.NEVER_RETRY)
                .target(PmRemoteService.class, "http://192.168.20.51:17004");
    }
}
