package com.LoveSea.fengCore.commons.utils;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * GsonUtils : Gson解析JSON
 */
@Slf4j
public class JsonUtils {

    private static final ThreadLocal<ObjectMapper> objectMapperLocal = ThreadLocal.withInitial(ObjectMapper::new);

    public static JsonNode readTree(String json) throws JsonProcessingException {
        return getMapper().readTree(json);
    }

    public static <T> T fromJson(String json, Class<T> clz) throws JsonProcessingException {
        return getMapper().readValue(json, clz);
    }

    public static <T> T fromJson(String json, TypeReference<T> valueTypeRef) throws JsonProcessingException {
        return getMapper().readValue(json, valueTypeRef);
    }

    private static ObjectMapper getMapper() {
        return objectMapperLocal.get();
    }
}
