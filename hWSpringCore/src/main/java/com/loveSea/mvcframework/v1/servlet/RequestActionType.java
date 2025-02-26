package com.loveSea.mvcframework.v1.servlet;

/**
 * @author xiahaifeng
 */

public enum RequestActionType {
    Get, Post, Put, Delete;

    public static RequestActionType getActionType(String actionType) {
        actionType = actionType.toUpperCase();
        return switch (actionType) {
            case "GET" -> Get;
            case "POST" -> Post;
            case "PUT" -> Put;
            case "DELETE" -> Delete;
            default -> null;
        };
    }
}