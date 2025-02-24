package com.loveSea.designPattern.templatePattern;

public abstract class MakePaintingTemplate {
    abstract void initialize();
    abstract void draw();
    public final void makePainting(){
        initialize();
        draw();
    }
}
