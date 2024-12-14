package com.loveSea.uidServer.service;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

/**
 * @author xiahaifeng
 */
@Service
@DubboService
public class RuleIdServiceImpl implements RuleIdService {
    private final UidGenerator uidGenerator = new UidGenerator(1, 2);

    @Override
    public Long getId() {
        return uidGenerator.generateId();
    }
}