package com.lovehai.helloSecurity.controller;

import com.lovehai.helloSecurity.entity.pm.HelloPm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * @author xiahaifeng
 */

@RestController
@RequestMapping("/demo")
public class DemoController {

    private static final Logger log = LoggerFactory.getLogger(DemoController.class);

    @GetMapping("/hello")
    public String hello(@ModelAttribute HelloPm pm) {
        log.info(pm.toString());
        return "Hello " + pm.getName() + "!";
    }
}