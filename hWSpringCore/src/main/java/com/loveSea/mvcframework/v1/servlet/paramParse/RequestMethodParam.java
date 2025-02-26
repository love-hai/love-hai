package com.loveSea.mvcframework.v1.servlet.paramParse;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;

import java.util.Map;

/**
 * @author xiahaifeng
 */
@Data
public class RequestMethodParam {
    private Class<?> type;
    private String name;
    private static ObjectMapper objectMapper = new ObjectMapper();
    private RequestMethodParamType requestMethodParamType;

    Object convert(Map<String, String[]> parameterMap, HttpServletRequest request, HttpServletResponse response) {
        switch (requestMethodParamType) {
            case HttpServletResponse:
                return response;
            case HttpServletRequest:
                return request;
            case RequestParam:
                String value = parameterMap.get(name)[0];
                return objectMapper.convertValue(value, type);
            case RequestBody:
                try {
                    return objectMapper.readValue(request.getInputStream(), type);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            default:
                return null;
        }
    }

    public enum RequestMethodParamType {
        RequestParam, RequestBody, HttpServletResponse, HttpServletRequest
    }
}