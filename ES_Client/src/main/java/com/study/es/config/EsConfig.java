package com.study.es.config;

import com.study.es.service.JestClientService;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by sk-qianxiao on 2018/3/9.
 */
@Configuration
@ConditionalOnClass(EsConfig.class)
public class EsConfig {
    @Value("${pm.es.server.uri:http://localhost:9200}")
    String esServerUri;
    @Value("${pm.es.username:elastic}")
    String username;
    @Value("${pm.es.password:S2Lylw_072nyLQxH@c3C}")
    String password;

    @Bean
    public JestClient jestClient() {
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(
                new HttpClientConfig.Builder(esServerUri)
                        .multiThreaded(true)
                        .defaultCredentials(username, password)
                        .build()
        );
        return factory.getObject();
    }

    @Bean
    public JestClientService jestClientService(){
        return new JestClientService();
    }
}