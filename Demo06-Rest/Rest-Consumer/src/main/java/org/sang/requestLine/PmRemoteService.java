package org.sang.requestLine;

import feign.Param;
import feign.RequestLine;

/**
 * PM 远程 REST API
 * @author sk-shifanwen
 * @date 2019/1/18
 */
public interface PmRemoteService {
    /**
     * 获取该用户下所有AC
     *
     * @param token     ism token
     * @param lang      多语言(zh-cn, en)
     * @param pageIndex pageIndex
     * @param pageSize  pageSize
     * @return AC详细信息
     */
    @RequestLine("GET /pm/ac/all?token={token}&lang={lang}&pageIndex={pageIndex}&pageSize={pageSize}")
    ResponseInfo<CustomAcInfoList> getAcListByUser(@Param("token") String token,
                                                   @Param("lang") String lang,
                                                   @Param("pageIndex") int pageIndex,
                                                   @Param("pageSize") int pageSize);
}
