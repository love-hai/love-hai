package com.lovehai.helloSecurity.entity.pm;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


/**
 * @author xiahaifeng
 */
@Data
public class HelloPm {
    @NotBlank(message = "name 不能为空")
    String name;
}