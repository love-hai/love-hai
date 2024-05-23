package com.loveSea.springDubbo.service;

import com.loveSea.springDubbo.api.DemoService;
import com.loveSea.springDubbo.mapstruct.TbUserMapstruct;
import com.loveSea.springDubbo.model.enitiy.TbUser;
import com.loveSea.springDubbo.model.vo.TbUserVo;
import com.loveSea.springDubbo.mybatis.mapper.TbUserMapper;
import com.loveSea.springDubbo.uidServer.enums.UidEnum;
import com.loveSea.springDubbo.uidServer.service.IdService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@DubboService
@Service
@Slf4j
public class DemoServiceImpl implements DemoService {
    @Autowired
    private TbUserMapper tbUserMapper;
    @DubboReference
    private IdService idService;

    @Override
    public TbUserVo sayHello(String name, String password) {
        log.info("sayHello:{}", name);
        TbUser tbUser = new TbUser();
        tbUser.setId(idService.getId(UidEnum.LOVE_SEA_TBUSER_ID));
        tbUser.setUserName(name);
        tbUser.setPassword(password);
        if(tbUserMapper.insert(tbUser) > 0 ){
            TbUserVo tbUserVo = TbUserMapstruct.INSTANCE.toVo(tbUser);
            log.info("save user:{}", tbUserVo);
            return tbUserVo;
        }
        return null;
    }
}