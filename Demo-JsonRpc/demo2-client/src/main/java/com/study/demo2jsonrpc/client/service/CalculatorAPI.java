package com.study.demo2jsonrpc.client.service;

import com.googlecode.jsonrpc4j.JsonRpcParam;

public interface CalculatorAPI {
    int add(@JsonRpcParam(value = "a") int a, @JsonRpcParam(value = "b") int b);
}
