package com.study.es.service;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.*;
import io.searchbox.indices.CreateIndex;
import io.searchbox.indices.DeleteIndex;
import io.searchbox.indices.mapping.PutMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * Created by sk-qianxiao on 2018/3/9.
 */
@Service
public class JestClientService {
    private final static Logger log = LoggerFactory.getLogger(JestClientService.class);

    @Autowired
    JestClient client;

    /**
     * 设置index _settings
     * @param index 索引名
     * @param settings 配置信息（json）
     * @return 请求结果
     * @throws IOException 请求异常
     */
    public JestResult settings(String index, String settings) throws IOException {
        CreateIndex createIndex = new CreateIndex.Builder(index).settings(settings).build();
        JestResult settingResult = client.execute(createIndex);
        log.info("settingResult: " + settingResult.getJsonString());
        return settingResult;
    }

    /**
     * 设置index映射
     * @param index 索引名
     * @param type 索引type，建议统一都用“doc”
     * @param mapping 映射配置信息（json）
     * @return 映射设置结果
     * @throws IOException 请求异常
     */
    public JestResult mapping(String index, String type, String mapping) throws IOException {
        CreateIndex createIndex = new CreateIndex.Builder(index).build();
        JestResult settingResult = client.execute(createIndex);
        log.info("createIndexResult: " + settingResult.getJsonString());

        PutMapping putMapping = new PutMapping.Builder(index, type, mapping).build();
        JestResult mappingResult = client.execute(putMapping);
        log.info("mappingResult: " + mappingResult.getJsonString());
        return mappingResult;
    }

    /**
     * 创建index、settings、 mapping
     * @param index 索引名
     * @param type 索引type，建议统一都用“doc”
     * @param settings 配置信息（json）
     * @param mapping 映射配置信息（json）
     * @throws IOException 请求异常
     */
    public void index(String index, String type, String settings, String mapping) throws IOException {
        CreateIndex createIndex = new CreateIndex.Builder(index).settings(settings).build();
        JestResult settingResult = client.execute(createIndex);
        log.info("settingResult: " + settingResult.getJsonString());

        PutMapping putMapping = new PutMapping.Builder(index, type, mapping).build();
        JestResult mappingResult = client.execute(putMapping);
        log.info("mappingResult: " + mappingResult.getJsonString());
    }


    /**
     * 添加数据
     * @param index 索引名
     * @param type 索引类型
     * @param source 需要索引的文档
     * @return 索引结果
     * @throws IOException 请求异常
     */
    public JestResult insert(String index, String type, String id, String source) throws IOException {
        if(null!=id){
            Index action = new Index.Builder(source).index(index).type(type).id(id).build();
            return client.execute(action);
        }
        else {
            Index action = new Index.Builder(source).index(index).type(type).build();
            return client.execute(action);
        }
    }

    /**
     * 批量添加数据
     * @param index 索引名
     * @param type 索引类型
     * @param sources 批量索引的文档集合
     * @return 批量索引结果
     * @throws IOException 请求异常
     */
    public JestResult bulk(String index, String type, List<Object> sources) throws IOException {
        Bulk.Builder builder = new Bulk.Builder().defaultIndex(index).defaultType(type);
        for (Object source : sources) {
            Index action = new Index.Builder(source).build();
            builder.addAction(action);
        }
        return client.execute(builder.build());
    }



     /**
     * 根据id删除文档
     * @param index 索引名
     * @param type 索引类型
     * @param id 索引id
     * @return 删除结果
     * @throws IOException 请求异常
     */
    public JestResult delete(String index, String type, String id) throws IOException {
        Delete delete = new Delete.Builder(id)
                .index(index)
                .type(type)
                .build();
        return client.execute(delete);
    }

    /**
     * 删除索引
     * @param index 索引名
     * @return 删除结果
     * @throws IOException 请求异常
     */
    public JestResult delete(String index) throws IOException {
        DeleteIndex deleteIndex = new DeleteIndex.Builder(index).build();
        return client.execute(deleteIndex);
    }

    /**
     * 更新文档
     * @param index 索引名
     * @param type 索引类型
     * @param id 索引id
     * @param script 更新逻辑脚本（groovy）
     * @return 更新结果
     * @throws IOException 请求异常
     */
    public JestResult update(String index, String type, String id, String script) throws IOException {
        Update update = new Update.Builder(script).index(index).type(type).id(id).build();
        return client.execute(update);
    }

    /**
     * 根据id获取文档内容
     * @param index 索引名
     * @param type 索引类型
     * @param id 索引id
     * @return 获取结果
     * @throws IOException 请求异常
     */
    public JestResult get(String index, String type, String id) throws IOException {
        Get get = new Get.Builder(index, id).type(type).build();
        return client.execute(get);
    }

    /**
     * 文档检索
     * @param index 索引名
     * @param type 索引类型
     * @param query 查询语句（Query DSL）
     * @return 检索结果
     * @throws IOException 请求异常
     */
    public JestResult search(String index, String type, String query) throws IOException {
        Search search = new Search.Builder(query)
                .addIndex(index)
                .addType(type)
                .build();

        return client.execute(search);
    }
}
