package com.loveSea.mvcframework.v1.servlet.handlerAdapter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author xiahaifeng
 */
@Data
public class RequestMethod {
    private Object object;
    private Method method;
    private RequestMethodParam[] params;

    public void accept(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        Object[] paramValues = new Object[params.length];
        for (int i = 0; i < params.length; i++) {
            Object paramValue = params[i].convert(parameterMap, request, response);
            paramValues[i] = paramValue;
        }
        try {
            Object result = method.invoke(object, paramValues);
            response.getWriter().write(result.toString());
        } catch (Exception e) {
            response.setStatus(500);
            e.printStackTrace();
        }
    }
}