package com.study.es;

import com.study.es.service.JestClientService;
import io.searchbox.client.JestResult;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JestClientServiceTest {
    private final static Logger log = LoggerFactory.getLogger(JestClientServiceTest.class);

    @Autowired
    JestClientService jestClientService;

    private String indexName = "index_test";
    private String indexType = "type_test";

    private String settings = "{" +
            "\"settings\": {" +
            "\"analysis\": {" +
            "\"analyzer\": {" +
            "\"ip_custom_analyzer\": {" +
            "\"type\": \"custom\"," +
            "\"tokenizer\": \"punctuation\"" +
            "}" +
            "}," +
            "\"tokenizer\": {" +
            "\"punctuation\": { " +
            "\"type\": \"pattern\"," +
            "\"pattern\": \"[ .,]\"" +
            "}" +
            "}" +
            "}" +
            "}" +
            "}";
    private String mapping = "{" +
            "\"doc\": {" +
            "\"properties\": {" +
            "\"internet_explorer\": {" +
            "\"type\": \"text\"," +
            "\"analyzer\": \"standard\"," +
            "\"search_analyzer\": \"standard\"" +
            "}," +
            "\"address\": {" +
            "\"type\": \"text\"," +
            "\"analyzer\": \"ik_max_word\"," +
            "\"search_analyzer\": \"ik_max_word\"" +
            "}," +
            "\"date\": {" +
            "\"type\": \"text\"," +
            "\"analyzer\": \"standard\"," +
            "\"search_analyzer\": \"standard\"" +
            "}," +
            "\"url\": {" +
            "\"type\": \"text\"," +
            "\"analyzer\": \"ik_max_word\"," +
            "\"search_analyzer\": \"ik_max_word\"" +
            "}," +
            "\"phone_number\": {" +
            "\"type\": \"text\"," +
            "\"analyzer\": \"standard\"," +
            "\"search_analyzer\": \"standard\"" +
            "}," +
            "\"ip\": {" +
            "\"type\": \"text\"," +
            "\"analyzer\": \"ip_custom_analyzer\"," +
            "\"search_analyzer\": \"ip_custom_analyzer\"" +
            "}," +
            "\"name\": {" +
            "\"type\": \"text\"," +
            "\"analyzer\": \"ik_max_word\"," +
            "\"search_analyzer\": \"ik_max_word\"" +
            "}," +
            "\"paragraph\": {" +
            "\"type\": \"text\"," +
            "\"analyzer\": \"ik_max_word\"," +
            "\"search_analyzer\": \"ik_max_word\"" +
            "}," +
            "\"job\": {" +
            "\"type\": \"text\"," +
            "\"analyzer\": \"standard\"," +
            "\"search_analyzer\": \"standard\"" +
            "}," +
            "\"mac_address\": {" +
            "\"type\": \"text\"," +
            "\"analyzer\": \"ik_max_word\"," +
            "\"search_analyzer\": \"ik_max_word\"" +
            "}" +
            "}" +
            "}" +
            "}";
    private String queryAllByPage = "{" +
            "\"from\": 0, " +
            "\"size\": 10, " +
            "\"query\": {" +
            "\"match_all\": {}" +
            "}" +
            "}";
    String testDocument = "{" +
            "\"name\": \"肖迁\"," +
            "\"job\": \"Researchscientist(physicalsciences)\"," +
            "\"phone_number\": \"14793843730\"," +
            "\"internet_explorer\": \"Mozilla/5.0(compatible;MSIE5.0;Windows95;Trident/3.0)\"," +
            "\"url\": \"https: //yan.org/\"," +
            "\"address\": \"西藏自治区琳县徐汇怀路i座233543\"," +
            "\"mac_address\": \"85:6a:aa:fb:68:7c\"," +
            "\"ip\": \"172.56.154.92\"," +
            "\"paragraph\": \"有关男人认为社区.出来一定女人记者都是地方经营.记者一次推荐开始广告.得到完成这么日期下载显示来源.最新组织这是就是.\"," +
            "\"date\": \"1976-05-20T15:50:16\"" +
            "}";
    private String updateScript = "{" +
            "    \"script\" : {" +
            "        \"source\": \"ctx._source.ip = params.new_ip\"," +
            "        \"params\" : {" +
            "            \"new_ip\" : \"0.0.0.0\"" +
            "        }" +
            "    }" +
            "}";

    private static String id;

    @Test
    public void a_indexTest() throws IOException {
        jestClientService.index(indexName, indexType, settings, mapping);
    }

    @Test
    public void b_insertTest() throws IOException {
        jestClientService.insert(indexName, indexType, "1", testDocument);
        JestResult result = jestClientService.insert(indexName, indexType, null, testDocument);
        id = result.getJsonObject().get("_id").getAsString();
        log.info("b_insertTest: " + result.getJsonString());
        log.info("id: " + id);
    }

    @Test
    public void c_bulkTest() throws IOException {
        List<Object> sources = new ArrayList<>();
        sources.add(testDocument);
        sources.add(testDocument);
        log.info("c_bulkTest: " + jestClientService.bulk(indexName, indexType, sources).getJsonString());
    }

    @Test
    public void g_deleteDocumentTest() throws IOException {
        log.info("g_deleteDocumentTest: " + jestClientService.delete(indexName, indexType, id).getJsonString());
    }

    @Test
    public void h_deleteIndexTest() throws IOException {
        log.info("h_deleteIndexTest: " + jestClientService.delete(indexName).getJsonString());
    }

    @Test
    public void f_updateTest() throws IOException {
        log.info("f_updateTest: " + jestClientService.update(indexName, indexType, id, updateScript).getJsonString());
    }

    @Test
    public void d_getTest() throws IOException {
        log.info("d_getTest: " + jestClientService.get(indexName, indexType, id).getJsonString());
    }

    @Test
    public void e_searchTest() throws IOException {
        log.info("e_searchTest: " + jestClientService.search(indexName, indexType, queryAllByPage).getJsonString());
    }
}
