package com.study.demo2jsonrpc.server.api;

import com.googlecode.jsonrpc4j.JsonRpcService;

@JsonRpcService("/greeting")
public interface Greeting {
    String sayHi();
}