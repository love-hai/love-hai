package com.lovehai.helloSecurity.service;

import com.lovehai.helloSecurity.HelloSecurityApplication;
import com.lovehai.helloSecurity.entity.UserAuthDetail;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author xiahaifeng
 */
@SpringBootTest(classes = HelloSecurityApplication.class)
@Slf4j
public class UserAuthDetailServiceTest {
    @Autowired
    private UserAuthDetailService userAuthDetailService;

    @Test
    void testInsert() {
        try {
            UserAuthDetail userAuthDetail = new UserAuthDetail();
            userAuthDetail.setId(1L);
            userAuthDetail.setUsername("test");
            userAuthDetail.setPassword("test");
            userAuthDetail.setRole("test");
            userAuthDetailService.insert(userAuthDetail);
        } catch (Exception e) {
            String message = e.getMessage();
            log.error("xxxxx"+e.getMessage());
            log.error("error", e);
        }
    }


}