package com.loveSea.uidServer.service;

import com.LoveSea.fengCore.retryable.Retryable;
import com.loveSea.uidServer.enums.UidEnum;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xiahaifeng
 */
@Service
@DubboService
public class IdServiceImpl implements IdService {
    @Autowired
    private UidGenerator uidGenerator;

    @Retryable
    public final Long getId(UidEnum uidEnum) {
        return uidGenerator.generate(uidEnum.getCode(), null, null, null);
    }

    @Retryable
    public UidEnum getUidEnum(UidEnum code, List<String> ab,String[] aa ,int[][][] bb) {
        return code;
    }
}
