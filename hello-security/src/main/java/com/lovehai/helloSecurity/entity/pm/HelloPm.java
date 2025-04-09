package com.lovehai.helloSecurity.entity.pm;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author xiahaifeng
 */
@Data
public class HelloPm {
    @NotBlank(message = "name 不能为空")
    String name;
}