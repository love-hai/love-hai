package com.LoveSea.fengCore.study.designPattern.factoryPattern;

public class PaintingFactory {
    public Painting getPainting(Byte type) {
        if (type == null) {
            return null;
        }
        if (type.equals(PaintingTypeEnum.FIGUREPAINTING.getCode())) {
            return new FigurePainting();
        } else if (type.equals(PaintingTypeEnum.LANDSCAPEPAINTING.getCode())) {
            return new LandscapePainting();
        }
        return null;
    }
}
