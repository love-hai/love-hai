package com.loveSea.springDubbo.model.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class TbUserVo implements Serializable {

    private Long id;

    private String userName;

    private String password;
}