package com.xq.study.json.jackson;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.xq.study.json.jackson.model.Family;
import com.xq.study.json.jackson.model.Son;


/**
 * Created by sk-qianxiao on 2019/4/19.
 */
public class JackSonMain {
    public static void main(String arg[]) {
        Son son = new Son();
        son.setSonName("SonName");
        son.setfName("FamilyName");
        System.out.println(son);

        try {
            ObjectMapper objectMapper = JsonUtils.getObjectMapper();
            String jsonDataString = objectMapper.writeValueAsString(son);
            System.out.println("Mapper序列化对象");
            System.out.println(jsonDataString);
            System.out.println("==================");

            Son son1 = JsonUtils.deserializeJson(objectMapper, jsonDataString, Son.class);
            Family family1 = JsonUtils.deserializeJson(objectMapper, jsonDataString, Family.class);
            System.out.println("Mapper反序列化出对象");
            System.out.println(son1);
            System.out.println(family1);
            System.out.println("==================");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void test1() {
        try {
            String tmp = "aaaaaaaaaaa";
            ObjectMapper objectMapper = JsonUtils.getObjectMapper();
            JsonNode node = objectMapper.readTree(tmp);
            ObjectNode newNode = objectMapper.createObjectNode();
            node.fields().forEachRemaining(entry -> {
                String fieldName = entry.getKey();      //获取键
                JsonNode field = entry.getValue();      //获取值
                String text = field.asText();           //值转string
                String newText = "aaa";
                newNode.set(fieldName, newNode.textNode(newText));  //设置新值
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
