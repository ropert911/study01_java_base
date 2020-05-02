package com.study.rest.consumer.event;

import com.study.rest.consumer.event.podo.ApArea;
import com.study.rest.consumer.event.response.ResponseInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ismapiTest {
    private static Logger logger = LoggerFactory.getLogger(BookTest.class);
    private static RestTemplate restTemplate = new RestTemplate();

    @Test
    public void queryAllAp() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://192.168.20.51:10015/inner/device/management/queryAllAp", String.class);
        logger.info("getHelloTest: getStatusCode=={}", responseEntity.getStatusCode());
        logger.info("getHelloTest: getStatusCodeValue=={}", responseEntity.getStatusCodeValue());
        logger.info("getHelloTest: getHeaders=={}", responseEntity.getHeaders());
        logger.info("getHelloTest: body=={}", responseEntity.getBody());
    }

    @Test
    public void queryAllUserDeviceListUseApMac() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://192.168.20.51:10015/inner/device/management/queryareaaplist?apMacs=01:02:03:04:05:0A", String.class);
        logger.info("queryareaaplist: body=={}", responseEntity.getBody());
    }


    @Test
    public void queryApAreaAllAp() {
        ResponseEntity<ResponseInfo> responseEntity = restTemplate.getForEntity("http://192.168.20.51:10015/inner/device/management/queryareaaplist?apMacs=01:02:03:04:05:0A", ResponseInfo.class);

        ResponseInfo<List<ApArea>> responseInfo = responseEntity.getBody();
        logger.info("queryareaaplist: body=={}", responseInfo);
    }

    public static Map<String, Long> getAllApTopArea(String ismServer, String innerPort) {
        logger.info("{}:{}", ismServer, innerPort);
        Map<String, Long> apAreaMap = new HashMap<>();

        RestTemplate restTemplate = new RestTemplate();
        String url = String.format("http://%s:%s/inner/device/management/queryareaaplist?queryall=true",
                ismServer,
                innerPort);

        ParameterizedTypeReference<ResponseInfo<List<ApArea>>> typeRef = new ParameterizedTypeReference<ResponseInfo<List<ApArea>>>() {
        };
        ResponseEntity<ResponseInfo<List<ApArea>>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, typeRef);
        ResponseInfo<List<ApArea>> responseInfo = responseEntity.getBody();
        if (true == responseInfo.getSuccess()) {
            List<ApArea> apAreaList = responseInfo.getData();
            logger.info("get result == {}", apAreaList);
            for (ApArea apArea : apAreaList) {
                apAreaMap.put(apArea.getMac(), apArea.getTopId());
            }
        } else {
            logger.error("get area info error. url={}, result={}", url, responseInfo);
        }

        return apAreaMap;
    }

    @Test
    public void getAllApTopAreaTest() {
        Map<String, Long> apAreaMap = getAllApTopArea("192.168.20.51", "10015");
        Iterator<Map.Entry<String, Long>> iterator = apAreaMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Long> entry = iterator.next();

            logger.info("{}=={}", entry.getKey(), entry.getValue());
        }
    }
}
