package com.LoveSea.fengCore.study.designPattern.decoratorPattern;

public class main {
    public static void main(String[] args) {
        Painting painting = new LandscapePainting();
        PictureFramePaintingColor pictureFramePaintingColor = new PictureFramePaintingColor(painting, "红色");
        PictureFramePaintingShape pictureFramePaintingShape = new PictureFramePaintingShape(pictureFramePaintingColor, "圆形");
        pictureFramePaintingShape.make();

    }
}
