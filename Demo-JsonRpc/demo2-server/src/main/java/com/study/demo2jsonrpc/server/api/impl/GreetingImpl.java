package com.study.demo2jsonrpc.server.api.impl;

import com.study.demo2jsonrpc.server.api.Greeting;
import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import org.springframework.stereotype.Service;

@Service
@AutoJsonRpcServiceImpl
public class GreetingImpl implements Greeting {
    @Override
    public String sayHi() {
        return "Greetings when testing out json-rpc";
    }
}
