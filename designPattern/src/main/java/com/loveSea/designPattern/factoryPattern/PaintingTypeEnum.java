package com.loveSea.designPattern.factoryPattern;

public enum PaintingTypeEnum {
    FIGUREPAINTING((byte) 0,"FigurePainting"),
    LANDSCAPEPAINTING((byte) 1,"LandscapePainting");
    private String msg;
    private Byte code;

    PaintingTypeEnum(Byte code, String msg){
        this.code = code;
        this.msg = msg;
    }
    public Byte getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static Byte getCode(String msg) {
        for (PaintingTypeEnum typeEnum : PaintingTypeEnum.values()) {
            if (typeEnum.getMsg().equals(msg)) {
                return typeEnum.getCode();
            }
        }
        return null;
    }

    public static String getMsg(Byte code) {
        for (PaintingTypeEnum typeEnum : PaintingTypeEnum.values()) {
            if (code.equals(typeEnum.getCode())) {
                return typeEnum.getMsg();
            }
        }
        return null;
    }

}
