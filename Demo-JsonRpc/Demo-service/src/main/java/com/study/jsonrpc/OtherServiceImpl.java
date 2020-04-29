package com.study.jsonrpc;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import org.springframework.stereotype.Component;

@AutoJsonRpcServiceImpl
@Component
public class OtherServiceImpl implements OtherService {
    public Boolean otherMethod() {
        System.out.println("this is other method");
        return Boolean.TRUE;
    }
}
