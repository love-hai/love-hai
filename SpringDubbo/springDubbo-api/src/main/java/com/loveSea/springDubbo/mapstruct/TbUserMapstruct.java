package com.loveSea.springDubbo.mapstruct;

import com.loveSea.springDubbo.model.enitiy.TbUser;
import com.loveSea.springDubbo.model.vo.TbUserVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author xiahaifeng
 */
@Mapper
public interface TbUserMapstruct {
    TbUserMapstruct INSTANCE = Mappers.getMapper(TbUserMapstruct.class);    // 字段类型映射修改

    TbUserVo toVo(TbUser tbUser);
}
