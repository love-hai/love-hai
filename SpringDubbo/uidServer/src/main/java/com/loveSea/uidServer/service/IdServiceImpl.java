package com.loveSea.uidServer.service;

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

    public final Long getId(UidEnum uidEnum) {
        return uidGenerator.generate(uidEnum.getCode(), null, null, null);
    }

    public <T> UidEnum getUidEnum(UidEnum code, List<? super UidEnum> uidEnums, T t) {
        return code;
    }
}
