package com.loveSea.mvcframework.v1.servlet;

/**
 * @author xiahaifeng
 */

public enum RequestActionType {
    GET, POST, PUT, DELETE;

    public static RequestActionType getActionType(String actionType) {
        actionType = actionType.toUpperCase();
        return switch (actionType) {
            case "GET" -> GET;
            case "POST" -> POST;
            case "PUT" -> PUT;
            case "DELETE" -> DELETE;
            default -> null;
        };
    }
}