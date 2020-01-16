package com.study.db.mongo.base.dao.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.UpdateOneModel;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.WriteModel;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.study.db.mongo.base.entity.User;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by summer on 2017/5/5.
 */
@Component
public class UserMutiDaoImpl {
    private Logger logger = LoggerFactory.getLogger(UserMutiDaoImpl.class);

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private MongoClient mongoClient;


    public void updateUserList(List<User> userList) {
        BulkOperations bulkOperations = mongoTemplate.bulkOps(BulkOperations.BulkMode.UNORDERED, User.class);
        userList.forEach(user -> {
            Query query = new Query(Criteria.where("id").is(user.getId()));
            Update update = new Update();
            update.set("userName", user.getUserName());
            update.set("passWord", user.getPassWord());
            update.set("sex", user.getSex());
            bulkOperations.updateOne(query, update);
        });
        bulkOperations.execute();
    }

    //使用示例：baseRepository.saveAndUpdate(apInfoList, ApInfo.class, "apBaseMac","assetNumber","comments","location","alias");
    public void saveAndUpdate(List<? extends Object> objectToSaves, Class<?> entityClass, String idName, String... delFields) {
        try {
            if (!CollectionUtils.isEmpty(objectToSaves)) {
                MongoCollection<Document> collection = mongoClient.getDatabase("pm-data-monitor").getCollection(entityClass.getAnnotation(org.springframework.data.mongodb.core.mapping.Document.class).collection());
                List<WriteModel<Document>> writeModels = getWriteModels(objectToSaves, idName,delFields);

                objectToSaves = null;

                if (!CollectionUtils.isEmpty(writeModels)) {
                    collection.bulkWrite(writeModels);
                }
            }
        } catch (DuplicateKeyException e) {
            logger.error("Ignore DuplicateKeyException: " + e.getMessage());
        }
    }

    private List<WriteModel<org.bson.Document>> getWriteModels (List<? extends Object> objects, String idName, String... delFields) {
        List<WriteModel<org.bson.Document>> requests = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(new MyPropertyNamingStrategy());
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objects.forEach(object -> {
            String json = null;
            try {
                json = objectMapper.writeValueAsString(object);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            org.bson.Document document = org.bson.Document.parse(json);

            //指定id对应实体类中的名称
            org.bson.Document queryDocument = new org.bson.Document("_id", document.get(idName));
            //将id从更新的对象中删除
            document.remove(idName);
            if(delFields.length > 0){
                for (int i=0; i<delFields.length; i++){
                    document.remove(delFields[i]);
                }
            }
            //指定更新内容
            org.bson.Document updateDocument = new org.bson.Document("$set", document);
            UpdateOneModel<Document> uom = new UpdateOneModel<org.bson.Document>(queryDocument,updateDocument,new UpdateOptions().upsert(true));
            requests.add(uom);
        });

        return requests;
    }
}
