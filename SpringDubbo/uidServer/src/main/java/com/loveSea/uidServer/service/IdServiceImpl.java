package com.loveSea.uidServer.service;

import com.LoveSea.fengCore.retryable.Retryable;
import com.loveSea.uidServer.enums.UidEnum;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author xiahaifeng
 */
@Service
@DubboService
public class IdServiceImpl implements IdService {
    @Autowired
    private UidGenerator uidGenerator;
    @Override
    @Retryable(maxRetries = 3, delay = 1000)
    @GetMapping
    public final Long getId(UidEnum uidEnum11) {
        return uidGenerator.generate(uidEnum11.getCode(),null,null,null);
    }
}
