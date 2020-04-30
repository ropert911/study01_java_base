package com.study.demo2jsonrpc.client.config;

import com.study.demo2jsonrpc.client.service.CalculatorAPI;
import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import com.googlecode.jsonrpc4j.ProxyUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class RpcClientConfig {
    private static final String endpoint = "http://localhost:8080/calculate";
    @Bean
    public JsonRpcHttpClient jsonRpcHttpClient() {
        URL url = null;
        //You can add authentication headers etc to this map
        Map<String, String> map = new HashMap<>();
        try {
            url = new URL(RpcClientConfig.endpoint);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new JsonRpcHttpClient(url, map);
    }

    @Bean
    public CalculatorAPI calculatorAPI(JsonRpcHttpClient jsonRpcHttpClient) {
        return ProxyUtil.createClientProxy(getClass().getClassLoader(), CalculatorAPI.class, jsonRpcHttpClient);
    }
}