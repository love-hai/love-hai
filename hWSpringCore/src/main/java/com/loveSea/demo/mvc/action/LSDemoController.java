package com.loveSea.demo.mvc.action;

import com.loveSea.demo.service.defn.LSDemoService;
import com.loveSea.mvcframework.annotation.*;

/**
 * @author xiahaifeng
 */
@LSController
@LSRequestMapping("/demo")
public class LSDemoController {
    @LSAutowired
    private LSDemoService lsDemoService;

    @LSGetMapping("/add")
    public Integer add(@LSRequestParam("a") Integer a, @LSRequestParam("b") Integer b) {
        return lsDemoService.add(a, b);
    }

    @LSGetMapping("/sub")
    public Integer sub(@LSRequestParam("a") Integer a, @LSRequestParam("b") Integer b) {
        return lsDemoService.sub(a, b);
    }

    @LSGetMapping("/getName")
    public String getName(@LSRequestParam("name") String name) {
        return "my name is " + name;
    }
}