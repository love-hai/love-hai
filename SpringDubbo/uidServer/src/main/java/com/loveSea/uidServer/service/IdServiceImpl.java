package com.loveSea.uidServer.service;

import com.LoveSea.fengCore.retryable.Retryable;
import com.loveSea.uidServer.enums.UidEnum;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * @author xiahaifeng
 */
@Service
@DubboService
public class IdServiceImpl implements IdService {
    @Autowired
    private UidGenerator uidGenerator;
    @Override
    @Retryable
    @GetMapping
    public final Long getId(@MatrixVariable("xiahhh") @ModelAttribute(name = "sss",value = "ssssss") UidEnum uidEnum11) {
        return uidGenerator.generate(uidEnum11.getCode(),null,null,null);
    }
}
