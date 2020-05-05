package com.study.mqtt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
public class MqttTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(MqttTestApplication.class, args);
    }

    @Value("${cm.mqtt.emq.username}")
    private String username;

    @Value("${cm.mqtt.emq.password}")
    private String password;

    @Bean(name = "mqttSubRestTemplate")
    public RestTemplate mqttSubRestTemplate(RestTemplateBuilder builder) {
        return builder.basicAuthentication(username, password).build();
    }
}
