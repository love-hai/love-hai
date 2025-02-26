package com.loveSea.demo.service.impl;

import com.loveSea.demo.service.defn.LSDemoService;
import com.loveSea.mvcframework.annotation.LSService;

/**
 * @author xiahaifeng
 */
@LSService
public class LSDemoServiceImpl implements LSDemoService {
    @Override
    public int add(int a, int b) {
        return a + b;
    }
}