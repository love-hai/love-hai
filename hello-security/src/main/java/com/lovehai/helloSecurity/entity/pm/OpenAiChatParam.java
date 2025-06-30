package com.lovehai.helloSecurity.entity.pm;

import lombok.Data;

import java.util.List;

/**
 * @author xiahaifeng
 */
@Data
public class OpenAiChatParam {
    private String aiPlatform;
    private String model;
    List<OpenAiMessageParam> msgList;
}