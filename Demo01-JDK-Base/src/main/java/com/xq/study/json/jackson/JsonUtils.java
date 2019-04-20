package com.xq.study.json.jackson;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;

import java.io.IOException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by sk-qianxiao on 2019/4/19.
 */
public class JsonUtils {
    /**
     * 得到默认的jackson转换类
     * @return
     */
    public static ObjectMapper getObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setDateFormat(new MultiDateFormat("yyyy-MM-dd HH:mm:ss"));
        return mapper;
    }
    /**
     * 得到首字母大写的jackson转换类
     * @return
     */
    public static ObjectMapper getUpperFirstObjectMapper() {
        ObjectMapper mapper = getObjectMapper();
        mapper.setPropertyNamingStrategy(new PropertyNamingStrategy() {
            // 使用大写来解析
            @Override
            public String nameForSetterMethod(MapperConfig<?> config,
                                              AnnotatedMethod method, String defaultName) {
                return method.getName().substring(3);
            }
        });
        return mapper;
    }

    /**
     * 反序列化对象
     * @param mapper
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T deserializeJson(ObjectMapper mapper, String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static class MultiDateFormat extends SimpleDateFormat {

        public MultiDateFormat(String pattern) {
            super(pattern);
        }

        @Override
        public Date parse(String source, ParsePosition pos) {
            if (source != null) {
                source = source.replace('T', ' ');
            }
            return super.parse(source, pos);
        }
    }
}
