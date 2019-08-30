package org.sang.requestLine;

import feign.Param;
import feign.RequestLine;
import org.sang.podo.CustomAcInfoList;
import org.sang.response.ResponseInfo;

/**
 * PM 远程 REST API
 * @author sk-shifanwen
 * @date 2019/1/18
 */
public interface MyBookService {
    @RequestLine("GET /book/hello3?name={name}")
    String sayHello(@Param("name") String name);
}
