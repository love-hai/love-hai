package com.loveSea.demo.mvc.action;

import com.loveSea.demo.service.defn.LSDemoService;
import com.loveSea.mvcframework.annotation.LSAutowired;
import com.loveSea.mvcframework.annotation.LSController;
import com.loveSea.mvcframework.annotation.LSRequestMapping;
import com.loveSea.mvcframework.annotation.LSRequestParam;

/**
 * @author xiahaifeng
 */
@LSController
@LSRequestMapping("/demo")
public class LSDemoController {
    @LSAutowired
    private LSDemoService lsDemoService;

    @LSRequestMapping("/add")
    public Integer add(@LSRequestParam("a") Integer a, @LSRequestParam("b") Integer b) {
        return lsDemoService.add(a, b);
    }
}