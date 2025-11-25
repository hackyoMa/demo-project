package com.github.demoproject.util;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tools.jackson.databind.json.JsonMapper;

import java.util.List;

/**
 * Json
 *
 * @author hackyo
 * @since 2022/4/1
 */
@Component
public class Json {

    public static JsonMapper jsonMapper;

    @Autowired
    public Json(JsonMapper jsonMapper) {
        Json.jsonMapper = jsonMapper;
    }

    public static <T> T toObject(ObjectNode objectNode, Class<T> clazz) {
        return jsonMapper.convertValue(objectNode, clazz);
    }

    public static <T> T toArray(ObjectNode objectNode, Class<T> clazz) {
        return jsonMapper.convertValue(objectNode, jsonMapper.getTypeFactory().constructCollectionType(List.class, clazz));
    }

    public static <T> T getObject(ObjectNode objectNode, String key, Class<T> clazz) {
        return jsonMapper.convertValue(objectNode.get(key), clazz);
    }

    public static <T> List<T> getArray(ObjectNode objectNode, String key, Class<T> clazz) {
        return jsonMapper.convertValue(objectNode.get(key), jsonMapper.getTypeFactory().constructCollectionType(List.class, clazz));
    }

    public static <T> T parseObject(String json, Class<T> clazz) {
        return jsonMapper.readValue(json, clazz);
    }

    public static <T> List<T> parseArray(String json, Class<T> clazz) {
        return jsonMapper.readValue(json, jsonMapper.getTypeFactory().constructCollectionType(List.class, clazz));
    }

    public static String toJsonString(Object object) {
        return jsonMapper.writeValueAsString(object);
    }

}
