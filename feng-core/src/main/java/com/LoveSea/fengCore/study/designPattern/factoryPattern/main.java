package com.LoveSea.fengCore.study.designPattern.factoryPattern;
public class main {
    private PaintingFactory paintingFactory=new PaintingFactory();
    public static void main(String[] args) {
        new main().test();
    }
    public void test(){
        Painting painting = paintingFactory.getPainting(PaintingTypeEnum.FIGUREPAINTING.getCode());
        painting.make();
        painting = paintingFactory.getPainting(PaintingTypeEnum.LANDSCAPEPAINTING.getCode());
        painting.make();
        if(painting instanceof LandscapePainting){
            ((LandscapePainting) painting).make2();
        }
    }
}
