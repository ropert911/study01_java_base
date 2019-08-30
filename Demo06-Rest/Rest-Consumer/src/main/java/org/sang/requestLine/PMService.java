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
public interface PMService {
    @RequestLine("GET /pm/ac/all?token={token}&lang={lang}&pageIndex={pageIndex}&pageSize={pageSize}")
    ResponseInfo<CustomAcInfoList> getAcListByUser(@Param("token") String token,
                                                   @Param("lang") String lang,
                                                   @Param("pageIndex") int pageIndex,
                                                   @Param("pageSize") int pageSize);
}