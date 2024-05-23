package com.loveSea.uidServer.service;

import com.loveSea.springDubbo.uidServer.enums.UidEnum;
import com.loveSea.springDubbo.uidServer.service.IdService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xiahaifeng
 */
@Service
@DubboService
public class IdServiceImpl implements IdService {
    @Autowired
    private UidGenerator uidGenerator;
    @Override
    public Long getId(UidEnum uidEnum) {
        return uidGenerator.generate(uidEnum.getCode());
    }
}
