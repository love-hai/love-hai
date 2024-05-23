package com.loveSea.springDubbo.api;


import com.loveSea.springDubbo.model.vo.TbUserVo;

public interface DemoService {

    TbUserVo sayHello(String name, String password);

}
