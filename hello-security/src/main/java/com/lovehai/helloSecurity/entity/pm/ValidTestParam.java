package com.lovehai.helloSecurity.entity.pm;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


/**
 * @author xiahaifeng
 */

@Data
public class ValidTestParam {
    @NotBlank(message = "name 不能为空")
    private String name;
    @Min(message = "age 不能小于0", value = 0)
    private int age;
}