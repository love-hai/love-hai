package com.loveSea.demo.mvc.action;

import com.loveSea.demo.service.defn.LSDemoService;
import com.loveSea.mvcframework.annotation.LSAutowired;
import com.loveSea.mvcframework.annotation.LSController;
import com.loveSea.mvcframework.annotation.LSRequestMapping;

/**
 * @author xiahaifeng
 */
@LSController
@LSRequestMapping("/demo")
public class LSDemoController {
    @LSAutowired
    private LSDemoService lsDemoService;

    public Integer add(Integer a, Integer b) {
        return lsDemoService.add(a, b);
    }
}