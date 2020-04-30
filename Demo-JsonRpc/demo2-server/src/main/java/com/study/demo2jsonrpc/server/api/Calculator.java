package com.study.demo2jsonrpc.server.api;

import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.JsonRpcService;

@JsonRpcService("/calculate")
public interface Calculator {
    int add(@JsonRpcParam(value = "a") int a, @JsonRpcParam(value = "b") int b);
}
