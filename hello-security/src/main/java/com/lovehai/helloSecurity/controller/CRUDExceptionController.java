package com.lovehai.helloSecurity.controller;

import com.lovehai.helloSecurity.entity.pm.ValidTestParam;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author xiahaifeng
 */
@RestController
@RequestMapping("/CRUD")
@Slf4j
public class CRUDExceptionController {

    @PostMapping("/valid/list/test")
    public String validListTest(@RequestBody @Valid @NotEmpty(message = "速卖通列表为空") List<ValidTestParam> items) {
        for (ValidTestParam item : items) {
            log.info(item.toString());
        }
        return "success";
    }

    @PostMapping("/valid/item/test")
    public String validItemTest(@Valid @RequestBody ValidTestParam item) {
        log.info(item.toString());
        return "success";
    }
}