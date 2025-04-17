package com.lovehai.helloSecurity.controller;

import com.lovehai.helloSecurity.entity.pm.ParmaTranTestDatePm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author xiahaifeng
 */
@RestController
@RequestMapping("/parma/tran")
@Slf4j
public class ParamTranController {

    @PostMapping("/test/date")
    public String testDate(@RequestBody ParmaTranTestDatePm pm) {
        log.info("date: {}", pm.toString());
        if (null != pm.getDate()) {
            return pm.toString();
        }
        return "fail";
    }
}