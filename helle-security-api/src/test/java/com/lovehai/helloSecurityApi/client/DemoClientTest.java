package com.lovehai.helloSecurityApi.client;

import com.lovehai.helloSecurityApi.SecurityApiApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author xiahaifeng
 */
@SpringBootTest(classes = SecurityApiApplication.class)
@Slf4j
public class DemoClientTest {
    @Autowired
    private DemoClient demoClient;

    @Test
    void testContinuousDate() {
        demoClient.continuousDate("xxx");
    }

}