package com.lovehai.helloSecurity.service;

import com.lovehai.helloSecurity.HelloSecurityApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author xiahaifeng
 */
@SpringBootTest(classes = HelloSecurityApplication.class)
@Slf4j
public class ListConfigValueService {
    @Value("${sites}")
    private List<String> sites;

    @Test
    public void testPrintSites() {
        for (String site : sites) {
            log.info("site:{}", site);
        }
    }
}