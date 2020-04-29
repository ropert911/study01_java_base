package com.study.jsonrpc;


import com.googlecode.jsonrpc4j.JsonRpcService;

@JsonRpcService("/otherService")
public interface OtherService {

    Boolean otherMethod();
}
