package com.LoveSea.fengCore.study.designPattern.decoratorPattern.example1;

public abstract class PictureFramePainting implements Painting {

    private Painting painting;

    public PictureFramePainting(Painting painting) {
        this.painting = painting;
    }

    public void make() {
        painting.make();
    }
}
