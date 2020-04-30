package com.study.jsonrpc.server;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import com.study.jsonrpc.OtherService;
import org.springframework.stereotype.Component;

/**
 * @author sk-qianxiao
 * @date 2020/4/30
 */
@AutoJsonRpcServiceImpl
@Component
public class OtherServiceImpl implements OtherService {
    @Override
    public Boolean otherMethod() {
        System.out.println("this is other method");
        return Boolean.TRUE;
    }
}
