package com.loveSea.mvcframework.v1.servlet;

import com.loveSea.mvcframework.v1.servlet.paramParse.RequestMethod;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiahaifeng
 */

public class UrlHandlerNode {
    private final Map<String, UrlHandlerNode> urlHandlerNodeMap = new HashMap<>();
    private final Map<RequestActionType, RequestMethod> requestMethodMap = new HashMap<>();

    public void addHandlerNode(String urlSlice, UrlHandlerNode urlHandlerNode) {
        urlHandlerNodeMap.put(urlSlice, urlHandlerNode);
    }

    public void addHandlerNodeByMethod(RequestActionType actionType, RequestMethod requestMethod) {
        requestMethodMap.put(actionType, requestMethod);
    }

    public UrlHandlerNode getHandlerNode(String urlSlice) {
        return urlHandlerNodeMap.get(urlSlice);
    }

    public RequestMethod getHandlerNodeByMethod(RequestActionType actionType) {
        return requestMethodMap.get(actionType);
    }
}