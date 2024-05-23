package com.loveSea.springDubbo.uidServer.enums;

public enum UidEnum {
    LOVE_SEA_TBUSER_ID(1, "love_sea_tbuser_id");

    private final Integer code;
    private final String desc;

    UidEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }
    public String getDesc() {
        return desc;
    }
}
