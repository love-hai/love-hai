package com.LoveSea.fengCore.study.designPattern.decoratorPattern;
public class PictureFramePaintingColor extends PictureFramePainting {

    private String Color;

    public PictureFramePaintingColor(Painting painting, String color) {
        super(painting);
        if (color == null || color.equals("")) {
            color = "白色";
        }
        this.Color = color;

    }

    @Override
    public void make() {
        super.make();
        System.out.println("画框颜色："+Color);
    }
}
