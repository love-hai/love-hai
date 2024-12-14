package com.loveSea.uidServer.service;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

/**
 * @author xiahaifeng
 */
@Service
@DubboService
public class UcIdServiceImpl implements UcIdService {
    private final UidGenerator uidGenerator = new UidGenerator(1, 1);

    public final Long getId() {
        return uidGenerator.generateId();
    }
}
