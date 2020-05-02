package com.study.rest.consumer.event.requestLine;

import com.study.rest.consumer.event.podo.CustomAcInfoList;
import feign.Param;
import feign.RequestLine;
import com.study.rest.consumer.event.response.ResponseInfo;

/**
 * PM 远程 REST API
 *
 * @date 2019/1/18
 */
public interface PMService {
    @RequestLine("GET /pm/ac/all?token={token}&lang={lang}&pageIndex={pageIndex}&pageSize={pageSize}")
    ResponseInfo<CustomAcInfoList> getAcListByUser(@Param("token") String token,
                                                   @Param("lang") String lang,
                                                   @Param("pageIndex") int pageIndex,
                                                   @Param("pageSize") int pageSize);
}
