package com.loveSea.configService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiahaifeng
 */

@RestController
public class ConfigController {

    @Value("${spring.application.name}")
    private String applicationName;

    @Autowired
    private Environment environment;

}