package com.lovehai.helloSecurityApi.config;

import feign.FeignException;
import feign.Response;
import feign.codec.Decoder;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

/**
 * 用于解析erp 返回值的解码器
 *
 * @author xiahaifeng
 */
@Slf4j
public class DataDecoder implements Decoder {

    @Override
    public Object decode(Response response, Type type) throws FeignException, IOException {
        InputStream inputStream = response.body().asInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            log.info(line);
            sb.append(line);
        }
        String responseBody = sb.toString();
        log.info("responseBody: {}", responseBody);
        return null;
    }

    BizReturnTypeInfo convertBizDataResponse(Type type) {
        return null;
    }

    // 基本类型装换
    private Type convertBasicType(Type clazz) {
        if (clazz == int.class) {
            return Integer.class;
        } else if (clazz == long.class) {
            return Long.class;
        } else if (clazz == float.class) {
            return Float.class;
        } else if (clazz == double.class) {
            return Double.class;
        } else if (clazz == boolean.class) {
            return Boolean.class;
        } else if (clazz == short.class) {
            return Short.class;
        } else if (clazz == byte.class) {
            return Byte.class;
        } else if (clazz == char.class) {
            return Character.class;
        }
        return clazz;
    }

    // 如过返回值不是BizDataResponse类型，转换为BizDataResponse类型
    static class BizReturnTypeInfo {
        private Type old_type;

        private Type new_type;
        private boolean type_change = false;

        public Type getOldType() {
            return old_type;
        }

        public Type getNewType() {
            return new_type;
        }

        public boolean getTypeChange() {
            return type_change;
        }
    }

}
