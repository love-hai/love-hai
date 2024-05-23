package com.LoveSea.fengCore.study.designPattern.decoratorPattern;

public class PictureFramePaintingShape extends PictureFramePainting {

    private String shape;
    public PictureFramePaintingShape(Painting painting, String shape) {
        super(painting);
        if (shape == null || shape.equals("")) {
            shape = "方形";
        }
        this.shape = shape;
    }

    @Override
    public void make() {
        super.make();
        System.out.println("画框形状："+shape);
    }
}
