package com.lovehai.helloSecurity.controller;

import com.lovehai.helloSecurity.entity.pm.HelloPm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.validation.Valid;
import java.util.Date;

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

    @PostMapping("/hello")
    public String helloPost(@RequestBody @Valid HelloPm pm) {
        log.info(pm.toString());
        return "Hello " + pm.getName() + "!";
    }


    @GetMapping("/continuous/date")
    public SseEmitter continuousDate(@ModelAttribute HelloPm pm) {
        SseEmitter emitter = new SseEmitter(60 * 1000L);
        emitter.onTimeout(() -> {
            log.warn("Request timed out");
            emitter.complete(); // 触发完成，避免后续写入
        });
        emitter.onCompletion(() -> {
            log.info("Emitter completed");
        });
        new Thread(() -> {
            try {
                while (true) {
                    Date now = new Date();
                    log.info(now.toString());
                    SseEmitter.SseEventBuilder eventBuilder = SseEmitter.event()
                            .data(now.toString())
                            .id(String.valueOf(now.getTime()))
                            .name("foo");
                    emitter.send(eventBuilder);
                    // 每秒检查一次日志更新
                    Thread.sleep(1000);
                }
            } catch (Exception e) {
                // 出现异常时结束响应并传递错误信息
                emitter.completeWithError(e);
                log.error(e.getMessage());
            }
        }).start();
        return emitter;
    }

    @GetMapping("/continuous/date1")
    public ResponseBodyEmitter continuousDate1(@ModelAttribute HelloPm pm) {
        ResponseBodyEmitter emitter = new ResponseBodyEmitter();
        emitter.onTimeout(() -> {
            log.warn("Request timed out");
            emitter.complete(); // 触发完成，避免后续写入
        });
        emitter.onCompletion(() -> {
            log.info("Emitter completed");
        });
        new Thread(() -> {
            try {
                while (true) {
                    Date now = new Date();
                    log.info(now.toString());
                    emitter.send(now.getTime() + "\n");
                    // 每秒检查一次日志更新
                    Thread.sleep(1000);
                }
            } catch (Exception e) {
                // 出现异常时结束响应并传递错误信息
                emitter.completeWithError(e);
                log.error(e.getMessage());
            }
        }).start();
        return emitter;
    }
}