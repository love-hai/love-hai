package com.lovehai.helloSecurity.entity.pm;

import lombok.Data;

/**
 * @author xiahaifeng
 */
@Data
public class OpenAiMessageParam {
    private String role;
    private String content;
}